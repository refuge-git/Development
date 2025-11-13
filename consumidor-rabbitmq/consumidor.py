# import pika
# import json
# import threading
# import smtplib
# import traceback
# from flask import Flask, jsonify
# from email.mime.text import MIMEText

# app = Flask(__name__)

# relatorios_presencas = []

# # RabbitMQ
# RABBITMQ_HOST = "13.222.51.1"
# RABBITMQ_USER = "myuser"
# RABBITMQ_PASS = "secret"
# QUEUE_NAME = "refuge.direct.queue"

# # E-mail
# EMAIL_SENDER = "fernandes.bia0703@gmail.com"
# EMAIL_PASSWORD = "ihni eqso tkxb vunc"
# SMTP_SERVER = "smtp.gmail.com"
# SMTP_PORT = 587

# def send_email(to_email, report_json):
#     """Envia um e-mail com os dados recebidos em formato legível"""
#     try:
#         if isinstance(report_json, list):
#             total = sum(int(item['quantidadePessoas']) for item in report_json)
#             dias_funcionamento = len(report_json)
#             media_diaria = round(total / dias_funcionamento, 2) if dias_funcionamento else 0
#             from datetime import datetime
#             mes_referencia = datetime.now().strftime('%m/%Y')
#             linhas = [
#                 "Relatório de Serviços do Espaço Social D'Achiropita",
#                 f"Mês de referência: {mes_referencia}\n"
#             ]
#             for item in report_json:
#                 linhas.append(
#                     f"Informe o número de pessoas que frequentaram o serviço por dia. Total no mês:Dias de funcionamento: {dias_funcionamento} Média Diária: [{item['dia']}][Qtd pessoas]\n{item['quantidadePessoas']}"
#                 )
#             email_body = "\n".join(linhas)
#         else:
#             email_body = f"Dados do relatório:\n{json.dumps(report_json, indent=2, ensure_ascii=False)}"

#         email_body += "\n\n---\nEste é um e-mail automático gerado pelo sistema."

#         msg = MIMEText(email_body, 'plain', 'utf-8')
#         msg['Subject'] = 'Relatório de Serviços Espaço Social Achiropita'
#         msg['From'] = EMAIL_SENDER
#         msg['To'] = to_email

#         with smtplib.SMTP(SMTP_SERVER, SMTP_PORT) as server:
#             server.starttls()
#             server.login(EMAIL_SENDER, EMAIL_PASSWORD)
#             server.send_message(msg)
#             print(f"[SUCCESS] E-mail enviado para {to_email}")
#     except Exception as e:
#         print(f"[ERRO] Falha ao enviar e-mail: {e}")
#         traceback.print_exc()

# def conectar_rabbitmq():
#     """Conecta ao RabbitMQ e retorna conexão e canal."""
#     print(f"[DEBUG] Conectando ao RabbitMQ em {RABBITMQ_HOST} com usuário '{RABBITMQ_USER}'...")
#     credentials = pika.PlainCredentials(RABBITMQ_USER, RABBITMQ_PASS)
#     try:
#         connection = pika.BlockingConnection(
#             pika.ConnectionParameters(host=RABBITMQ_HOST, credentials=credentials)
#         )
#         channel = connection.channel()
#         print("[DEBUG] Conexão estabelecida com sucesso.")
#         channel.queue_declare(queue=QUEUE_NAME, durable=True)
#         print(f"[DEBUG] Fila '{QUEUE_NAME}' declarada.")
#         return connection, channel
#     except Exception as e:
#         print(f"[ERRO] Falha ao conectar no RabbitMQ: {e}")
#         raise

# def callback(ch, method, properties, body):
#     """Função chamada quando chega uma mensagem na fila."""
#     print(f"[DEBUG] Mensagem recebida! Body: {body}")
#     try:
#         mensagem = json.loads(body)  # decodifica JSON
#         print(f"[DEBUG] Mensagem decodificada como JSON: {type(mensagem)}")
#     except json.JSONDecodeError as e:
#         print(f"[DEBUG] Erro ao decodificar JSON: {e}")
#         mensagem = body.decode()
#         print(f"[DEBUG] Mensagem decodificada como string: {mensagem}")
    
#     relatorios_presencas.append(mensagem)
#     print(f"[DEBUG] Total de relatórios armazenados: {len(relatorios_presencas)}")
    
#     if isinstance(mensagem, dict) and 'email' in mensagem and 'presencas' in mensagem:
#         destinatario = mensagem['email']
#         presencas = mensagem['presencas']
        
#         print(f"[DEBUG] Destinatário do e-mail: {destinatario}")
#         print(f"✅ Relatório recebido ({len(presencas)} registros):")
#         print(json.dumps(mensagem, indent=2, ensure_ascii=False))
        
#         if destinatario and '@' in destinatario:
#             print(f"[DEBUG] Enviando e-mail para: {destinatario}")
#             send_email(destinatario, presencas)
#         else:
#             print(f"[ERRO] Destinatário inválido: {destinatario}. E-mail não enviado.")
#     else:
#         print("[ERRO] Mensagem recebida não tem os campos esperados ('email' e 'presencas'). E-mail não enviado.")

# # Conecta ao RabbitMQ
# connection, channel = conectar_rabbitmq()

# def iniciar_consumidor():
#     print("🎧 Consumidor de relatórios iniciado. Aguardando mensagens...")
#     try:
#         print(f"[DEBUG] Consumindo da fila '{QUEUE_NAME}'...")
#         channel.basic_consume(queue=QUEUE_NAME, on_message_callback=callback, auto_ack=True)
#         channel.start_consuming()
#     except Exception as e:
#         print(f"[ERRO] Falha ao consumir mensagens: {e}")

# # Executa o consumidor em uma thread separada
# threading.Thread(target=iniciar_consumidor, daemon=True).start()

# # Endpoint para visualizar relatórios recebidos
# @app.route("/relatorios/recebidos", methods=["GET"])
# def obter_relatorios():
#     print(f"[DEBUG] Endpoint /relatorios/recebidos chamado. Total de relatórios: {len(relatorios_presencas)}")
#     return jsonify(relatorios_presencas), 200

# # Endpoint para verificar o status da conexão
# @app.route("/status", methods=["GET"])
# def status():
#     return jsonify({
#         "status": "running",
#         "relatorios_count": len(relatorios_presencas),
#         "queue": QUEUE_NAME,
#         "host": RABBITMQ_HOST
#     }), 200

# if __name__ == "__main__":
#     print("Consumidor Flask rodando na porta 5000")
#     app.run(port=5000)

import pika
import json
import threading
import smtplib
import traceback
from flask import Flask, jsonify
from email.mime.text import MIMEText

app = Flask(__name__)

relatorios_presencas = []

# RabbitMQ
RABBITMQ_HOST = "localhost"
RABBITMQ_USER = "myuser"
RABBITMQ_PASS = "secret"
QUEUE_NAME = "refuge.direct.queue"

# E-mail
EMAIL_SENDER = "fernandes.bia0703@gmail.com"
EMAIL_PASSWORD = "ihni eqso tkxb vunc"
SMTP_SERVER = "smtp.gmail.com"
SMTP_PORT = 587

def send_email(to_email, report_json):
    """Envia um e-mail com os dados recebidos em formato legível"""
    try:
        if isinstance(report_json, list):
            total = sum(int(item['quantidadePessoas']) for item in report_json)
            dias_funcionamento = len(report_json)
            media_diaria = round(total / dias_funcionamento, 2) if dias_funcionamento else 0
            linhas = ["Relatório de Serviços do Espaço Social D'Achiropita\n"]
            for item in report_json:
                linhas.append(
                    f"Informe o número de pessoas que frequentaram o serviço por dia. Total no mês:Dias de funcionamento: {dias_funcionamento} Média Diária: [{item['dia']}][Qtd pessoas]\n{item['quantidadePessoas']}"
                )
            email_body = "\n".join(linhas)
        else:
            email_body = f"Dados do relatório:\n{json.dumps(report_json, indent=2, ensure_ascii=False)}"

        email_body += "\n\n---\nEste é um e-mail automático gerado pelo sistema."

        msg = MIMEText(email_body, 'plain', 'utf-8')
        msg['Subject'] = 'Relatório de Serviços Espaço Social Achiropita'
        msg['From'] = EMAIL_SENDER
        msg['To'] = to_email

        with smtplib.SMTP(SMTP_SERVER, SMTP_PORT) as server:
            server.starttls()
            server.login(EMAIL_SENDER, EMAIL_PASSWORD)
            server.send_message(msg)
            print(f"[SUCCESS] E-mail enviado para {to_email}")
    except Exception as e:
        print(f"[ERRO] Falha ao enviar e-mail: {e}")
        traceback.print_exc()

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
        channel.queue_declare(queue=QUEUE_NAME, durable=True)
        print(f"[DEBUG] Fila '{QUEUE_NAME}' declarada.")
        return connection, channel
    except Exception as e:
        print(f"[ERRO] Falha ao conectar no RabbitMQ: {e}")
        raise

def callback(ch, method, properties, body):
    """Função chamada quando chega uma mensagem na fila."""
    print(f"[DEBUG] Mensagem recebida! Body: {body}")
    try:
        mensagem = json.loads(body)  # decodifica JSON
        print(f"[DEBUG] Mensagem decodificada como JSON: {type(mensagem)}")
    except json.JSONDecodeError as e:
        print(f"[DEBUG] Erro ao decodificar JSON: {e}")
        mensagem = body.decode()
        print(f"[DEBUG] Mensagem decodificada como string: {mensagem}")

    relatorios_presencas.append(mensagem)
    print(f"[DEBUG] Total de relatórios armazenados: {len(relatorios_presencas)}")

   
    if isinstance(mensagem, dict) and 'email' in mensagem and 'presencas' in mensagem:
        destinatario = mensagem['email']
        presencas = mensagem['presencas']
       
        print(f"[DEBUG] Destinatário do e-mail: {destinatario}")
       
        print(f"✅ Relatório recebido ({len(presencas)} registros):")
        print(json.dumps(mensagem, indent=2, ensure_ascii=False))
       
        if destinatario and '@' in destinatario:
            print(f"[DEBUG] Enviando e-mail para: {destinatario}")
            send_email(destinatario, presencas)
        else:
            print(f"[ERRO] Destinatário inválido: {destinatario}. E-mail não enviado.")
    else:
        print("[ERRO] Mensagem recebida não tem os campos esperados ('email' e 'presencas'). E-mail não enviado.")

# Conecta ao RabbitMQ
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

# Endpoint para visualizar relatórios recebidos
@app.route("/relatorios/recebidos", methods=["GET"])
def obter_relatorios():
    print(f"[DEBUG] Endpoint /relatorios/recebidos chamado. Total de relatórios: {len(relatorios_presencas)}")
    return jsonify(relatorios_presencas), 200

# Endpoint para verificar o status da conexão
@app.route("/status", methods=["GET"])
def status():
    return jsonify({
        "status": "running",
        "relatorios_count": len(relatorios_presencas),
        "queue": QUEUE_NAME,
        "host": RABBITMQ_HOST
    }), 200

if __name__ == "__main__":
    print("Consumidor Flask rodando na porta 5000")
    app.run(port=5000)