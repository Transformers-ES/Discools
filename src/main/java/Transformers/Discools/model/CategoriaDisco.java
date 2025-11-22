package Transformers.Discools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDisco {
    private int id;
    private String nomeCategoriaDisco;
    private List<DiscosCD> discosCd;

    public CategoriaDisco(int id, String nomeCategoriaDisco, List<DiscosCD> discosCd) {
        this.id = id;
        this.nomeCategoriaDisco = nomeCategoriaDisco;
        this.discosCd = discosCd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    @JsonProperty("nome")
    public String getNomeCategoriaDisco() {
        return nomeCategoriaDisco;
    }

    public void setNomeCategoriaDisco(String nomeCategoriaDisco) {
        this.nomeCategoriaDisco = nomeCategoriaDisco;
    }

    public List<DiscosCD> getDiscosCd() {
        return discosCd;
    }

    public void setDiscosCd(List<DiscosCD> discosCd) {
        this.discosCd = discosCd;
    }
}