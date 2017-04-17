package br.univel;


@Tabela("CAD_ALUNO")
public class Aluno {
	
	@Coluna(pk=true)
	private int id;
	@Coluna(nome="CL_NOME")
    private String nome;
	@Coluna(nome="CL_NOTA")
    private double nota;

    public Aluno(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    

}
