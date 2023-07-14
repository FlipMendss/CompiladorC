package felipe.org.example;

import java.util.ArrayList;
import java.util.List;

public class TabelaSimbolos {

    private int memoria;

    private List<Registro> registros = new ArrayList<>();
    private TabelaSimbolos tabelaPai;

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public TabelaSimbolos getTabelaPai() {
        return tabelaPai;
    }

    public void setTabelaPai(TabelaSimbolos tabelaPai) {
        this.tabelaPai = tabelaPai;
    }

    public void inserirRegistro(Registro registro) {
        registros.add(registro);
    }

    @Override
    public String toString() {
        return "memoria: " + memoria + "\nregistros: " + registros + "\ntabelaPai: " + tabelaPai;
    }

    public boolean jaTemIdentificador(Token t) {
        for (Registro registro : registros) {
            if (registro.getNome().equals(t.getValor().getValorID())) {
                return true;
            }
        }
        return false;
    }

    public boolean jaTemIdentificadorRecursiva(Token t) {
        if (jaTemIdentificador(t)) {
            return true;
        } else {
            if (tabelaPai != null) {
                return tabelaPai.jaTemIdentificadorRecursiva(t);
            } else {
                return false;
            }
        }
    }

    public Registro getIdentificador(Token t) {
        for (Registro registro : registros) {
            if (registro.getNome().equals(t.getValor().getValorID())) {
                return registro;
            }
        }
        return null;
    }

    public Registro getIdentificadorRecursiva(Token t) {
        Registro registro = getIdentificador(t);
        if (registro != null) {
            return registro;
        } else {
            if (tabelaPai != null) {
                return tabelaPai.getIdentificadorRecursiva(t);
            } else {
                return null;
            }
        }
    }

    public List<Registro> getRegistros() {
        return registros;
    }





}
