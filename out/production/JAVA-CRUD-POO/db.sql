create table Curso(
codcurso int primary key not null,
nome varchar(100) not null
);
create table Aluno(
matricula int primary key not null,
nome varchar(100) not null,
idade int not null,
fkcurso int not null,
CONSTRAINT fk_aluno_curso
	FOREIGN KEY (fkcurso) REFERENCES Curso(codcurso)
);

create table Disciplina(

);