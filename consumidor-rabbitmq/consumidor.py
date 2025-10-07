import pika
import json
import threading
from flask import Flask, jsonify

app = Flask(__name__)

relatorios_presencas = []

RABBITMQ_HOST = "localhost"       
RABBITMQ_USER = "myuser"            
RABBITMQ_PASS = "secret"            
QUEUE_NAME = "refuge.direct.queue"  

def conectar_rabbitmq():
    """Conecta ao RabbitMQ e retorna conexão e canal."""
    print(f"[DEBUG] Conectando ao RabbitMQ em {RABBITMQ_HOST} com usuário '{RABBITMQ_USER}'...")
    credentials = pika.PlainCredentials(RABBITMQ_USER, RABBITMQ_PASS)
    try:
        connection = pika.BlockingConnection(
            pika.ConnectionParameters(host=RABBITMQ_HOST, credentials=credentials)
        )
        channel = connection.channel()
        print("[DEBUG] Conexão estabelecida com sucesso.")
        # Garante que a fila existe
        channel.queue_declare(queue=QUEUE_NAME, durable=True)
        print(f"[DEBUG] Fila '{QUEUE_NAME}' declarada.")
        return connection, channel
    except Exception as e:
        print(f"[ERRO] Falha ao conectar no RabbitMQ: {e}")
        raise

def callback(ch, method, properties, body):
    """Função chamada quando chega uma mensagem na fila."""
    try:
        mensagem = json.loads(body)  # decodifica JSON
    except json.JSONDecodeError:
        mensagem = body.decode()
    relatorios_presencas.append(mensagem)
    print(f"✅ Relatório recebido ({len(mensagem)} registros):")
    print(json.dumps(mensagem, indent=2, ensure_ascii=False))

connection, channel = conectar_rabbitmq()

def iniciar_consumidor():
    print("🎧 Consumidor de relatórios iniciado. Aguardando mensagens...")
    try:
        print(f"[DEBUG] Consumindo da fila '{QUEUE_NAME}'...")
        channel.basic_consume(queue=QUEUE_NAME, on_message_callback=callback, auto_ack=True)
        channel.start_consuming()
    except Exception as e:
        print(f"[ERRO] Falha ao consumir mensagens: {e}")

# Executa o consumidor em uma thread separada
threading.Thread(target=iniciar_consumidor, daemon=True).start()

# Endpoint para visualizar relatórios recebidos via browser ou Postman
@app.route("/relatorios/recebidos", methods=["GET"])
def obter_relatorios():
    return jsonify(relatorios_presencas), 200

if __name__ == "__main__":
    print("Consumidor Flask rodando na porta 5000")
    app.run(port=5000)
