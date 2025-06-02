package school.sptech.refuge.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.refuge.repository.TipoSexualidadeRepository;
import school.sptech.refuge.service.TipoSexualidadeService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TipoSexualidadeTest {

    @InjectMocks
    private TipoSexualidadeService sexualidadeService;

    @Mock
    private TipoSexualidadeRepository sexualidadeRepository;



}