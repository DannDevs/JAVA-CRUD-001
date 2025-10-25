Create database dbcurso;

use dbcurso;

create table Curso(
codcurso int primary key not null,
nome varchar(100) not null,
turno varchar(10) not null
);
create table Aluno(
matricula int primary key not null,
nome varchar(100) not null,
idade int not null,
fkcurso int not null,
FOREIGN KEY (fkcurso) references curso(codcurso)

);

create table disciplina(
coddisciplina int primary key not null,
nome VARCHAR(255) not null,
cargahoraria int not null
);

create table cursodisciplina(
coddisciplina int,
codcurso int,
PRIMARY KEY(coddisciplina,codcurso),
FOREIGN KEY (coddisciplina) REFERENCES Disciplina(coddisciplina),
FOREIGN KEY (codcurso) REFERENCES Curso(codcurso)
)
