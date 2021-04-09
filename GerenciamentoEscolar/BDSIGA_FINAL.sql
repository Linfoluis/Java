CREATE DATABASE siga
use siga
go

create table aluno 
(
	ra int not null primary key,
	nome varchar(100) not null
)

insert into aluno values
(1, 'JOÃO'),
(2, 'JOHN'),
(3, 'MILENE'),
(4, 'TIÃO'),
(5, 'ALFRED'),
(6, 'BOBBY'),
(7, 'THEODORO'),
(8, 'LUCAS'),
(9 ,'ROBERT')
select * from aluno


create table disciplina
(
	codigo int not null primary key,
	nome varchar(50) not null,
	sigla varchar(10) not null,
	turno char(1) not null,
	numaulas int not null
)

select * from notas
select n.nota from notas n inner join avaliacao av on n.aval_codigo = av.codigo where n.disci_codigo = 4203010 and av.tipo = 'P1'
insert into disciplina values(4203010, 'Arquitetura e Organização de Computadores', 'AOC', 'T', 80)
insert into disciplina values(4203020, 'Arquitetura e Organização de Computadores', 'AOC', 'N', 80)
insert into disciplina values(4208010, 'Laboratório de Hardware', 'LAB.HD.', 'T', 40)
insert into disciplina values(4226004, 'Banco de Dados', 'BD', 'T', 80)
insert into disciplina values(4213003, 'Sistemas Operacionais I', 'SO I', 'T', 80)
insert into disciplina values(4213013, 'Sistemas Operacionais I', 'SO I', 'N', 80)
insert into disciplina values(4233005, 'Laboratório de Banco de Dados', 'Lab BD', 'T', 80)
insert into disciplina values(5005220, 'Métodos Para a Produção do Conhecimento', 'MPC', 'T', 40)
select * from disciplina

CREATE TABLE avaliacao
(
	cod_disc			   int				not null,
	codigo				   int				not null,
	tipo				   char(2)	    	not null,
	peso				   decimal(4,2)		not null,
	PRIMARY KEY (codigo),
	FOREIGN KEY (cod_disc) references disciplina(codigo)
)
select * from avaliacao

CREATE TABLE faltas 
(
	aluno_ra int		   not null,
	disci_codigo int	   not null,
	datas datetime		   not null,
	presencas smallint	   not null,
	Primary key (aluno_ra,disci_codigo,datas),
	FOREIGN KEY (aluno_ra) references aluno(ra),
	FOREIGN KEY (disci_codigo) references disciplina(codigo)
)
select * from faltas

CREATE TABLE notas
(
	aluno_ra	  int not null,
	disci_codigo  int not null,
	aval_codigo	  int not null,
	nota		 decimal(4,2) not null
	Primary key (aluno_ra,disci_codigo, aval_codigo),
	FOREIGN KEY (aluno_ra) references aluno(ra),
	FOREIGN KEY (disci_codigo) references disciplina(codigo),
	FOREIGN KEY (aval_codigo) references avaliacao(codigo)
)
select * from notas


/* a) Fazer o sistema, com Stored Procedure para inserção de notas e pesos;  */
create procedure sp_Pesos
@disc_codigo			int,
@aval_codigo			int,				
@aval_tipo				char(2),
@aval_peso				decimal(3,2)
as
begin
 insert into avaliacao(cod_disc,codigo, tipo, peso) values (@disc_codigo, @aval_codigo, @aval_tipo, @aval_peso)
end


--INSERE OS PESOS PARA CADA AVALIAÇÃO PARA CADA DISCIPLINA
--DISICPLINA, TIPO DE AV, PESO, OPÇÃO I = INSERE OU U = ATUALIZA

select  from notas n where n.disci_codigo= 4203010
--Arquitetura e Organização de Computadores T
exec sp_Pesos 4203010, 1, 'P1', 0.30
exec sp_Pesos 4203010, 2, 'P2', 0.50
exec sp_Pesos 4203010, 3, 'P3', 0.30
exec sp_Pesos 4203010, 4, 'TB', 0.20

--Arquitetura e Organização de Computadores N
exec sp_Pesos 4203020, 5, 'P1', 0.30
exec sp_Pesos 4203020, 6, 'P2', 0.50
exec sp_Pesos 4203020, 7, 'P3', 0.30
exec sp_Pesos 4203020, 8, 'TB', 0.30

--Laboratório de Hardware T
exec sp_Pesos 4208010, 9, 'P1', 0.30
exec sp_Pesos 4208010, 10, 'P2', 0.50
exec sp_Pesos 4208010, 11, 'P3', 0.30
exec sp_Pesos 4208010, 12, 'TB', 0.30

--Sistemas Operacionais I N
exec sp_Pesos 4213003, 13, 'P1', 0.35
exec sp_Pesos 4213003, 14, 'P2', 0.35
exec sp_Pesos 4213003, 15, 'TB', 0.30
exec sp_Pesos 4213003, 16, 'PE', 0.20

--Sistemas Operacionais I T
exec sp_Pesos 4213013, 17, 'P1', 0.35
exec sp_Pesos 4213013, 18, 'P2', 0.35
exec sp_Pesos 4213013, 19, 'TB', 0.30
exec sp_Pesos 4213013, 20, 'PE', 0.20

--Banco de Dados T 
exec sp_Pesos 4226004, 21, 'P1', 0.30
exec sp_Pesos 4226004, 22, 'P2', 0.50
exec sp_Pesos 4226004, 23, 'P3', 0.30
exec sp_Pesos 4226004, 24, 'TB', 0.30

--Laboratório de Banco de Dados T
exec sp_Pesos 4233005, 25, 'P1', 0.33
exec sp_Pesos 4233005, 26, 'P2', 0.33
exec sp_Pesos 4233005, 27, 'P3', 0.33
exec sp_Pesos 4233005, 28, 'TB', 0.20

--Métodos Para a Produção do Conhecimento
exec sp_Pesos 5005220, 29, 'MO', 0.20
exec sp_Pesos 5005220, 30, 'MC', 0.80

select * from avaliacao

select * from notas
select * from disciplina
select * from disciplina where codigo = 4203010
select * from avaliacao where disc_cod = 4213013

--PROCEDURE CRIADA PARA INSERÇÃO DE NOTAS PARA CADA ALUNO DE CADA DISCIPLINA
create  procedure sp_Notas
@aluno_ra		 int,
@disci_codigo	 int,
@aval_codigo	 int,
@nota			 decimal(3,2)
as
begin
		insert into notas values (@aluno_ra, @disci_codigo, @aval_codigo, @nota)	
end

--INSERE NOTAS SENDO RA, DISCIPLINA, TIPO DE AV, NOTA
exec sp_Notas 1, 4203010, 1, 9
exec sp_Notas 1, 4203010, 2, 9
exec sp_Notas 5, 4203010, 1, 9
exec sp_Notas 5, 4203010, 2, 9
exec sp_Notas 5, 4203010, 3, 9
exec sp_Notas 1, 4203010, 4, 9
exec sp_Notas 2, 4213003, 13, 8
exec sp_Notas 3, 4208010, 9, 7
exec sp_Notas 4, 4213003, 13, 2
exec sp_Notas 4, 4213003, 14, 2
exec sp_Notas 4, 4213003, 15, 2
exec sp_Notas 4, 4213003, 16, 2
exec sp_Notas 1, 4203010, 3, 3
exec sp_Notas 1, 5005220, 29, 3 
exec sp_Notas 2, 5005220, 29, 3
exec sp_Notas 1, 5005220, 30, 9
exec sp_Notas 2, 5005220, 30, 9

select * from notas

select * from avaliacao
select * from aluno
select * from disciplina

select * from faltas
-- QUESTÃO B
create procedure sp_faltas
@aluno_ra		int, 
@disci_cod		int,
@faltas			smallint,
@datas			date,
@disci_numaulas int
as
	begin
	Set @disci_numaulas = (select d.numaulas from disciplina d where d.codigo = @disci_cod)
		if(@disci_numaulas = 80 and @faltas <= 4)
			begin 
				insert into faltas(aluno_ra,disci_codigo,datas,presencas) Values (@aluno_ra,@disci_cod,@datas,@faltas)
			end
		if(@disci_numaulas = 40 and @faltas <= 2)
			begin 
				insert into faltas(aluno_ra,disci_codigo,datas,presencas) Values (@aluno_ra,@disci_cod,@datas,@faltas)
			end
	end


exec sp_faltas 1, 4203010, 80, 2, '14/06/2020'
exec sp_faltas 1, 4203010, 80, 4, '15/06/2020'
exec sp_faltas 5, 4203010, 80, 3, '14/06/2020' 
exec sp_faltas 5, 4203010, 80, 3, '15/06/2020' 
exec sp_faltas 5, 4203010, 80, 2, '16/06/2020' 
exec sp_faltas 5, 4203010, 80, 2, '17/06/2020' 
exec sp_faltas 5, 4203010, 80, 2, '18/06/2020' 



select * from aluno
select * from disciplina
select * from faltas


/* c) Apresentar em tela, a saída de uma UDF, com cursor, que apresenta um quadro com as notas da turma:  */
/* (RA_Aluno, Nome_Aluno, Nota1, Nota2, ..., Média_Final, Situação(Aprovado ou Reprovado)) */
--

CREATE FUNCTION f_Cursor(@disc int)
RETURNS @Tabela table(
ra		int,
aluno	varchar(100),
N1		decimal(7,2),
N2		decimal(7,2),
N3		decimal(7,2),
TB		decimal(7,2),
PRE		decimal(7,2),
MO		decimal(7,2),
MC		decimal(7,2),
media	decimal(7,2),
sit	    varchar(20)
)
AS
BEGIN
DECLARE @ra int, @aluno varchar(100) 
DECLARE C_Media CURSOR FOR SELECT distinct(al.ra), al.nome from aluno al left join matricula m on m.aluno_ra = al.ra and m.Disciplina_codigo = @disc
OPEN C_Media
WHILE @@FETCH_STATUS  = 0 
BEGIN
FETCH NEXT FROM C_Media INTO @ra, @aluno
	INSERT INTO @Tabela(ra, aluno) 
	select distinct(al.ra), al.nome from aluno al inner join
	notas nt on al.ra=nt.aluno_ra 
	where nt.disci_codigo=@disc and nt.aluno_ra = @ra
	declare @peso1 decimal(4,2)=0, @peso2 decimal(4,2)=0, @peso3 decimal(4,2)=0, @peso4 decimal(4,2)=0, @peso5 decimal(4,2)=0
	update @tabela set MO=0, MC=0, N1=0, N2=0, N3=0, PRE=0, TB=0, MEDIA = 0
	update @tabela set n1 = nt.nota, media += nt.nota * av.peso from notas nt inner join avaliacao av on nt.disci_codigo = 4203010 and aval_codigo = codigo where tipo = 'P1' and peso is not null and nt.aluno_ra=ra
	update @tabela set n2 = nt.nota, media += nt.nota * av.peso from notas nt inner join avaliacao av on nt.disci_codigo = 4203010 and aval_codigo = codigo where tipo = 'P2' and peso is not null and nt.aluno_ra=ra
	update @tabela set n3 = nt.nota, media += nt.nota * av.peso from notas nt inner join avaliacao av on nt.disci_codigo = 4203010 and aval_codigo = codigo where tipo = 'P3' and peso is not null and nt.aluno_ra=ra
	update @tabela set tb = nt.nota, media += nt.nota * av.peso from notas nt inner join avaliacao av on nt.disci_codigo = 4203010 and aval_codigo = codigo where tipo = 'TB' and peso is not null and nt.aluno_ra=ra
	update @tabela set PRE = nt.nota, media += nt.nota * av.peso from notas nt inner join avaliacao av on nt.disci_codigo = 4203010 and aval_codigo = codigo where tipo = 'PE' and peso is not null and nt.aluno_ra=ra
	update @tabela set MO = nt.nota, media = media + nt.nota * av.peso from notas nt inner join avaliacao av on disci_codigo = @disc and aval_codigo = codigo where tipo = 'MO' and peso is not null and nt.aluno_ra=ra
	update @tabela set MC = nt.nota, media = media + nt.nota * av.peso from notas nt inner join avaliacao av on disci_codigo = @disc and aval_codigo = codigo where tipo = 'MC' and peso is not null and nt.aluno_ra=ra
	update @Tabela set sit = 'Aprovado' where media > 6
	update @Tabela set sit = 'Reprovado' where media < 6 
END	
CLOSE C_Media
DEALLOCATE C_Media
RETURN
END

select * from f_Cursor(4213003)
select * from avaliacao
select * from aluno
select * from disciplina
select * from notas
select * from faltas
select * from avaliacao

/* d) Apresentar, em tela, a saída de uma UDF, com cursor, que apresenta um quadro com as faltas da turma, sendo que, para cada data deverá haver um F por falta e um P por presença: */
/* (RA_Aluno, Nome_Aluno, Data1, Data2, ..., Total_Faltas)  */

select * from faltas

create function F_faltas(@disc int)
RETURNS @Faltas Table(
ra			int,
nome		varchar(100),
calendario	nvarchar(11),
faltas		nvarchar(4),
total		smallint
)
as
BEGIN
DECLARE @ra int, @nome varchar(100)
DECLARE C_Faltas CURSOR FOR SELECT ra, nome from aluno
OPEN C_Faltas
FETCH NEXT FROM C_Faltas INTO @ra, @nome
INSERT INTO @Faltas(ra, nome, calendario, total)
SELECT al.ra, al.nome, convert(nvarchar(11), f.datas,103), f.presencas from aluno al inner join faltas f
on al.ra = f.aluno_ra inner join disciplina d
on d.codigo = f.disci_codigo 
where d.codigo = @disc and al.ra = f.aluno_ra 
begin
update @Faltas set faltas = SUBSTRING('PPPPFFFF', total+1, 4)
end
RETURN
END

select * from F_faltas(4203010)
select * from f_Cursor(4203010)

select * from disciplina
select * from faltas

insert into faltas(disci_codigo, aluno_ra, datas, presencas) values
(4203010, 1, '16/06/2020', 2),
(4203010, 5, '16/06/2020', 3),
(4203010, 1, '14/06/2020', 0),
(4203010, 5, '14/06/2020', 0),
(4203010, 1, '15/06/2020', 1),
(4203010, 5, '15/06/2020', 4)

select * from faltas
select * from aluno
select * from disciplina

/* e) Apresentar, em browser se Web ou abrir o arquivo , se desktop, um relatório em PDF da mesma UDF da saída de tela de notas */

/* f) Apresentar, em browser se Web ou abrir o arquivo , se desktop,  um relatório em PDF da mesma UDF da saída de tela de faltas */

/* g) Fazer um gatilho que identifique se uma disciplina estiver sendo atualizada ou excluída, lance um erro e não permita que a operação ocorra */

CREATE TRIGGER t_block on disciplina
for Update , Delete
as
begin
	declare
	@aux int
	set @aux =(select count(*) from deleted)
	if(@aux>0)
		BEGIN	
					ROLLBACK TRANSACTION
					RAISERROR ('Não é permitido alterar ou excluir as disciplinas ',16,2)
		end
end

INSERT INTO disciplina(codigo,nome, sigla, turno, numaulas,curso_codigo) 
VALUES (0, 'ANALISE E DESENVOLVIMENTO DE SOFTWARE', 'ADS', 'T', 80, 1),
	   (1, 'COMERCIO EXTERIOR', 'COM', 'N', 80,1),
	   (2, 'POLIMEROS', 'POL', 'M', 80,1),
	   (3, 'LOGISTICA', 'LOG', 'T', 80,1)
select * from disciplina

update disciplina set codigo = 112 where codigo = 1
delete disciplina where codigo = 1


-------------------------------------
-------------------------------------
--/* LOGIN ADICIONADO (OPCIONAL) */--
-------------------------------------
-------------------------------------

create table logar
(
	RG varchar(9) primary key not null,
	senha varchar(30) not null
)
go
select * from logar

insert into logar values ('1234', 'admin')


create procedure sp_validaLogin(@RG varchar(9), @senha varchar(30), @Resultado varchar(30)output)
as
Declare
	@validaRG varchar(9),
	@validaSenha varchar(30)
	Set @validaRG = (select RG from logar where RG = @RG)
	Set @validaSenha = (select senha from logar where senha = @senha)
	if(@RG = @validaRG)
	Begin
		if(@senha = @validaSenha)
			Begin
				Set @Resultado = 'Conectado!'
			End
	End
	else
	Begin
		Set @Resultado = 'Usuário / Senha incorretos !'
	End
	
create table matricula
(
	aluno_ra int not null,
	Disciplina_codigo int not null,
	Primary Key(aluno_ra, Disciplina_codigo),
	Foreign Key(aluno_ra) references aluno(ra),
	Foreign Key(Disciplina_codigo) references disciplina(codigo)
)
go

insert into matricula values(1, 4203010)
insert into matricula values(2, 4203010)
insert into matricula values(3, 4203010)
insert into matricula values(4, 4226004)
insert into matricula values(5, 4226004)
insert into matricula values(1, 4203010)
insert into matricula values(2, 4203020)
insert into matricula values(3, 4208010)
insert into matricula values(4, 4226004)
insert into matricula values(5, 4213003)
insert into matricula values(6, 4213013)
insert into matricula values(7, 4233005)
insert into matricula values(8, 5005220)
insert into matricula values(9, 4203010)

select * from disciplina
select * from faltas

