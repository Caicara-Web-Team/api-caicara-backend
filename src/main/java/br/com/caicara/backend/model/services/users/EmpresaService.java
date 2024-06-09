package br.com.caicara.backend.model.services.users;

import br.com.caicara.backend.model.dto.users.EmpresaUpdateDto;
import br.com.caicara.backend.model.dto.users.RibeirinhoUpdateDto;
import br.com.caicara.backend.model.entities.users.Empresa;
import br.com.caicara.backend.model.entities.users.Ribeirinho;
import br.com.caicara.backend.model.repositories.users.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Empresa createEmpresa(Empresa emp){
        return empresaRepository.save(emp);
    }

    @Transactional(readOnly = true)
    public Empresa getUserById(UUID id) {
        return empresaRepository.findByUsersId(id);
    }

    @Transactional(readOnly = true)
    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }

    @Transactional(readOnly = true)

    public Empresa getEmpresaById(UUID id) {
        return empresaRepository.findById(id).orElseThrow((() -> new RuntimeException("Id não encontrado")));
    }

    @Transactional
    public void updateEmpresa(UUID id, EmpresaUpdateDto dto) {
        Empresa emp = empresaRepository.findById(id).orElseThrow(()-> new RuntimeException("Id não encontrado"));
        modelMapper.map(dto, emp);
        empresaRepository.save(emp);
    }
}
