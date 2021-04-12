create database Sistema_vendas
go
use Sistema_vendas

CREATE TABLE usuario(
usuario varchar(100) PRIMARY KEY,
senha	varchar(100),
permissao bit)

CREATE TABLE produto(
id			INT PRIMARY KEY IDENTITY,
nome		VARCHAR(100) NOT NULL,
descricao	VARCHAR(max) NOT NULL,
preco		DECIMAL(7,2) NOT NULL)

CREATE TABLE venda(
id				INT			 NOT NULL,
usuario		VARCHAR(100) NOT NULL,
id_produto      INT			 NOT NULL,
data			datetime	 NOT NULL,
quantidade		INT			 NOT NULL,
total			DECIMAL(7,2) NOT NULL,
Constraint pk_Venda PRIMARY KEY (id, usuario, id_produto),
FOREIGN KEY (usuario) references usuario(usuario))

create procedure sp_insereVenda (@id int, @usuario varchar(100), @id_produto int, @qtd smallint)
as
begin
	declare @data datetime = getdate(),
			@valor_total decimal (7,2) = (SELECT preco from produto where id = @id_produto) * @qtd

	insert into venda(id, usuario, id_produto, data, quantidade, total)
			select @id, @usuario, @id_produto, @data, @qtd, @valor_total
end

