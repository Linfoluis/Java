package entity;

public class Usuario {

	private long id;
	private String usuario;
	private String senha;
	private boolean permissao = false;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setPermissao(boolean permissao) {
		this.permissao = permissao;
	}
	
	public boolean temPermissaoAdmin() {
		return permissao;
	}
	
}
