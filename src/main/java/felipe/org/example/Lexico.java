package felipe.org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexico {
    private String caminhoArquivo;
    private String nomeArquivo;
    private int c;
    PushbackReader br;
    BufferedReader initialBr;
    private List<String> reservadas = new ArrayList<String>(Arrays.asList(
            "and", "array", "begin", "case", "const", "div",
            "do", "downto", "else", "end", "file", "for",
            "function", "goto", "if", "in", "label", "mod",
            "nil", "not", "of", "or", "packed", "procedure",
            "program", "record", "repeat", "set", "then",
            "to", "type", "until", "var", "while", "with",
            "read", "write", "real", "integer"
    ));

    public Lexico(String nomeArquivo) {
        this.caminhoArquivo = Paths.get(nomeArquivo).toAbsolutePath().toString();
        this.nomeArquivo = nomeArquivo;

        try {
            this.initialBr = new BufferedReader(new FileReader(caminhoArquivo, StandardCharsets.UTF_8));
            this.br = new PushbackReader(initialBr);
            this.c = this.br.read();
        } catch (IOException err) {
            System.err.println("Não foi possível abrir o arquivo ou ler do arquivo: " + this.nomeArquivo);
            err.printStackTrace();
        }
    }

    public Token getToken(int linha, int coluna) {
        int tokenSize = 0;
        int numEspacos = 0;
        int e;
        StringBuilder lexema = new StringBuilder("");
        char caracter;
        Token token = new Token();

        try {

            while (c != -1) {
                caracter = (char) c;
                if (!(c == 13 || c == 10)) {
                    if (caracter == ' ') {
                        while (caracter == ' ') {
                            c = this.br.read();
                            numEspacos++;
                            caracter = (char) c;
                        }
                    } else if (Character.isLetter(caracter)) {
                        while (Character.isLetter(caracter) || Character.isDigit(caracter)) {
                            lexema.append(caracter);
                            c = this.br.read();
                            tokenSize++;
                            caracter = (char) c;
                        }

                        if (returnIfIsReservedWord(lexema.toString())) {
                            token.setClasse(Classe.cPalRes);
                        } else {
                            token.setClasse(Classe.cId);
                        }
                        token.settokenSize(tokenSize);
                        token.setColuna(coluna + numEspacos);
                        token.setLinha(linha);
                        Valor valor = new Valor(lexema.toString());
                        token.setValor(valor);
                        return token;
                    } else if (Character.isDigit(caracter)) {
                        int numPontos = 0;
                        while (Character.isDigit(caracter) || caracter == '.') {
                            if (caracter == '.') {
                                numPontos++;
                            }
                            lexema.append(caracter);
                            c = this.br.read();
                            tokenSize++;
                            caracter = (char) c;
                        }
                        if (numPontos <= 1) {
                            if (numPontos == 0) {
                                token.setClasse(Classe.cInt);
                                Valor valor = new Valor(Integer.parseInt(lexema.toString()));
                                token.setValor(valor);
                            } else {
                                token.setClasse(Classe.cReal);
                                Valor valor = new Valor(Float.parseFloat(lexema.toString()));
                                token.setValor(valor);
                            }

                            token.settokenSize(tokenSize);
                            token.setColuna(coluna + numEspacos);
                            token.setLinha(linha);
                            return token;
                        }
                    } else {
                        tokenSize++;
                        if (caracter == ':') {
                            int proximo = this.br.read();
                            caracter = (char) proximo;
                            if (caracter == '=') {
                                tokenSize++;
                                token.setClasse(Classe.cAtribuicao);
                            } else {
                                this.br.unread(proximo);
                                token.setClasse(Classe.cDoisPontos);
                            }
                        } else if (caracter == '+') {
                            token.setClasse(Classe.cMais);
                        } else if (caracter == '-') {
                            token.setClasse(Classe.cMenos);
                        } else if (caracter == '/') {
                            token.setClasse(Classe.cDivisao);
                        } else if (caracter == '*') {
                            token.setClasse(Classe.cMultiplicacao);
                        } else if (caracter == '>') {
                            int proximo = this.br.read();
                            caracter = (char) proximo;
                            if (caracter == '=') {
                                tokenSize++;
                                token.setClasse(Classe.cMaiorIgual);
                            } else {
                                this.br.unread(proximo);
                                token.setClasse(Classe.cMaior);
                            }
                        } else if (caracter == '<') {
                            int proximo = this.br.read();
                            caracter = (char) proximo;
                            if (caracter == '=') {
                                tokenSize++;
                                token.setClasse(Classe.cMenorIgual);
                            } else if (caracter == '>') {
                                tokenSize++;
                                token.setClasse(Classe.cDiferente);
                            } else {
                                this.br.unread(proximo);
                                token.setClasse(Classe.cMenor);
                            }
                        } else if (caracter == '=') {
                            token.setClasse(Classe.cIgual);
                        } else if (caracter == ',') {
                            token.setClasse(Classe.cVirgula);
                        } else if (caracter == ';') {
                            token.setClasse(Classe.cPontoVirgula);
                        } else if (caracter == '.') {
                            token.setClasse(Classe.cPonto);
                        } else if (caracter == '(') {
                            token.setClasse(Classe.cParEsq);
                        } else if (caracter == ')') {
                            token.setClasse(Classe.cParDir);
                        } else {
                            token.setClasse(Classe.cEOF);
                        }
                        token.settokenSize(tokenSize);
                        token.setColuna(coluna + numEspacos);
                        token.setLinha(linha);
                        token.setValor(null);
                        c = this.br.read();
                        tokenSize++;
                        return token;
                    }
                } else {
                    c = this.br.read();
                    linha++;
                    numEspacos = 0;
                    tokenSize = 0;
                    coluna = 1;
                }
            }

            token.setClasse(Classe.cEOF);
            return token;
        } catch (IOException err) {
            System.err.println("Não foi possível abrir o arquivo ou ler do arquivo: " + this.nomeArquivo);
            err.printStackTrace();
        }
        return null;
    }

    boolean returnIfIsReservedWord(String word) {
        return this.reservadas.contains(word.toLowerCase());

    }
}
