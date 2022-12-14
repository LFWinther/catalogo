package org.winther.backend.wintherburguer.dto;

import org.springframework.web.client.RestTemplate;
import org.winther.backend.wintherburguer.enums.Estado;
import org.winther.backend.wintherburguer.model.Endereco;

public class EnderecoDTO {

    private String cep;
    private String numero;
    private String complemento;

    public Endereco toEndereco() {
        Endereco endereco = new Endereco();
        endereco.setCep(this.cep);
        endereco.setNumero(this.numero);
        endereco.setComplemento(this.complemento);

        String uri = "https://viacep.com.br/ws/" + this.cep + "/json/";

        RestTemplate rest = new RestTemplate();
        EnderecoViaCepDTO viaCep = rest.getForObject(uri, EnderecoViaCepDTO.class);

        endereco.setEstado(Estado.valueOf(viaCep.getUf()));
        endereco.setCidade(viaCep.getLocalidade());
        endereco.setBairro(viaCep.getBairro());
        endereco.setRua(viaCep.getLogradouro());
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}
