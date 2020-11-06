package br.com.forum.api.config;

public class ErrorFormDto {
	
	private String campo;
	private String erro;
	
	public ErrorFormDto(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}	

}
