insert into omapp4.permissao (id, nome, descricao) values (001, 'CONSULTAR_ESTADOS', 'Permite listar ou buscar estados');
insert into omapp4.permissao (id, nome, descricao) values (002, 'EDITAR_ESTADOS', 'Permite criar ou editar estados');
insert into omapp4.permissao (id, nome, descricao) values (003, 'CONSULTAR_CIDADES', 'Permite lista ou buscar cidades');
insert into omapp4.permissao (id, nome, descricao) values (004, 'EDITAR_CIDADES', 'Permite criar ou editar cidades');

insert into omapp4.permissao (id, nome, descricao) values (005, 'CONSULTAR_PESSOAS', 'Permite listar pessoas ou buscar umna pessoa');
insert into omapp4.permissao (id, nome, descricao) values (006, 'EDITAR_PESSOAS', 'Permite editar pessoas');
insert into omapp4.permissao (id, nome, descricao) values (007, 'CONSULTAR_GRUPOS_PERMISSOES', 'Permite listar ou buscar grupos e suas permissões');
insert into omapp4.permissao (id, nome, descricao) values (008, 'EDITAR_GRUPOS_PERMISSOES', 'Permite criar ou editar grupos e suas permissões');
insert into omapp4.permissao (id, nome, descricao) values (009, 'CONSULTAR_PESSOAS_GRUPOS', 'Permite consultar grupos de pessoas');
insert into omapp4.permissao (id, nome, descricao) values (010, 'EDITAR_PESSOAS_GRUPOS', 'Permite criar ou editar grupos de pessoas');

insert into omapp4.permissao (id, nome, descricao) values (011, 'EDITAR_ENDERECOS', 'Permite criar ou editar endereços');

insert into omapp4.permissao (id, nome, descricao) values (012, 'CONSULTAR_PRODUTOS', 'Permite consultar produtos');
insert into omapp4.permissao (id, nome, descricao) values (013, 'EDITAR_PRODUTOS', 'Permite criar, editar e apagar produtos');

insert into omapp4.permissao (id, nome, descricao) values (014, 'CONSULTAR_MANUTENCOES', 'Permite consultar manutenções');
insert into omapp4.permissao (id, nome, descricao) values (015, 'EDITAR_MANUTENCOES', 'Permite criar, editar e apagar manutenções');

insert into omapp4.permissao (id, nome, descricao) values (016, 'CONSULTAR_COMPRAS', 'Permite consultar compras');
insert into omapp4.permissao (id, nome, descricao) values (017, 'EDITAR_COMPRAS', 'Permite criar, editar e apagar compras');

insert into omapp4.permissao (id, nome, descricao) values (018, 'CONSULTAR_VENDAS', 'Permite consultar vendas');
insert into omapp4.permissao (id, nome, descricao) values (019, 'EDITAR_VENDAS', 'Permite criar, editar e apagar vendas');

insert into omapp4.permissao (id, nome, descricao) values (020, 'CONSULTAR_ENTRADAS', 'Permite consultar entradas');
insert into omapp4.permissao (id, nome, descricao) values (021, 'EDITAR_ENTRADAS', 'Permite criar, editar e apagar entradas');

insert into omapp4.permissao (id, nome, descricao) values (022, 'CONSULTAR_SAIDAS', 'Permite consultar saidas');
insert into omapp4.permissao (id, nome, descricao) values (023, 'EDITAR_SAIDAS', 'Permite criar, editar e apagar saidas');

insert into omapp4.permissao (id, nome, descricao) values (024, 'CONSULTAR_CONTASPAGAR', 'Permite consultar contaspagar');
insert into omapp4.permissao (id, nome, descricao) values (025, 'EDITAR_CONTASPAGAR', 'Permite criar, editar e apagar contaspagar');

insert into omapp4.permissao (id, nome, descricao) values (026, 'CONSULTAR_CONTASRECEBER', 'Permite consultar contasreceber');
insert into omapp4.permissao (id, nome, descricao) values (027, 'EDITAR_CONTASRECEBER', 'Permite criar, editar e apagar contasreceber');

insert into omapp4.permissao (id, nome, descricao) values (028, 'CONSULTAR_CAIXAS', 'Permite consultar caixas');
insert into omapp4.permissao (id, nome, descricao) values (029, 'EDITAR_CAIXAS', 'Permite criar, editar e apagar caixas');

insert into omapp4.permissao (id, nome, descricao) values (030, 'CONSULTAR_ESTOQUES', 'Permite consultar estoques');
insert into omapp4.permissao (id, nome, descricao) values (031, 'EDITAR_ESTOQUES', 'Permite criar, editar e apagar estoques');

insert into omapp4.permissao (id, nome, descricao) values (032, 'CONSULTAR_FINANCEIROS', 'Permite consultar financeiros');
insert into omapp4.permissao (id, nome, descricao) values (033, 'EDITAR_FINANCEIROS', 'Permite criar, editar e apagar financeiros');

insert into omapp4.permissao (id, nome, descricao) values (034, 'CONSULTAR_FINANCEIROS_PESSOAIS', 'Permite consultar finaceiros pessoais');
insert into omapp4.permissao (id, nome, descricao) values (035, 'EDITAR_FINANCEIROS_PESSOAIS', 'Permite criar, editar e apagar financeiros pessoais');

insert into omapp4.grupo (id,nome) values(1,'Gestor');
insert into omapp4.grupo (id,nome) values(2,'Inscrito');
insert into omapp4.grupo (id,nome) values(3,'Cliente');
insert into omapp4.grupo (id,nome) values(4,'Fornecedor');
insert into omapp4.grupo (id,nome) values(5,'Vendedor');
insert into omapp4.grupo (id,nome) values(6,'Mecanico');


insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,1);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,2);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,3);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,4);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,5);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,6);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,7);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,8);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,9);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,10);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,11);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,12);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,13);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,14);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,15);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,16);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,17);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,18);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,19);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,20);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,21);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,22);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,23);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,24);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,25);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,26);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,27);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,28);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,29);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,30);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,31);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,32);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,33);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,34);
insert into omapp4.grupo_permissao (grupo_id, permissao_id) values(1,35);



INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (01,  'AC', 'Acre');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (02,  'AL', 'Alagoas');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (03,  'AP', 'Amapá');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (04,  'AM', 'Amazonas');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (05,  'BA', 'Bahia');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (06,  'CE', 'Ceará');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (07,  'DF', 'Distrito Federal');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (08,  'ES', 'Espírito Santo');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (09,  'GO', 'Goiás');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (10,  'MA', 'Maranhão');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (11,  'MT', 'Mato Grosso');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (12,  'MS', 'Mato Grosso do Sul');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (13,  'MG', 'Minas Gerais');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (14,  'PA', 'Pará');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (15,  'PB', 'Paraíba');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (16,  'PR', 'Paraná');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (17,  'PE', 'Pernambuco');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (18,  'PI', 'Piauí');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (19,  'RJ', 'Rio de Janeiro');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (20,  'RN', 'Rio Grande do Norte');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (21,  'RS', 'Rio Grande do Sul');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (22,  'RO', 'Rondônia');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (23,  'RR', 'Roraima');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (24,  'SC', 'Santa Catarina');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (25,  'SP', 'São Paulo');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (26,  'SE', 'Sergipe');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (27,  'TO', 'Tocantins');
INSERT INTO omapp4.estado (id,  sigla, nom_estado) VALUES (28,  'NC', 'Não cadastrado');



/* ********************** E s p í r i t o   S a n t o *********************** */

INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0802, 08, 'Afonso Cláudio');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0803, 08, 'Água Doce do Norte');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0804, 08, 'Águia Branca');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0805, 08, 'Alegre');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0806, 08, 'Alfredo Chaves');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0807, 08, 'Alto Rio Novo');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0808, 08, 'Anchieta');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0809, 08, 'Apiacá');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0810, 08, 'Aracruz');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0811, 08, 'Atilio Vivacqua');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0812, 08, 'Baixo Guandu');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0813, 08, 'Barra de São Francisco');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0814, 08, 'Boa Esperança');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0815, 08, 'Bom Jesus do Norte');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0816, 08, 'Brejetuba');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0817, 08, 'Cachoeiro de Itapemirim');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0818, 08, 'Cariacica');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0819, 08, 'Castelo');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0820, 08, 'Colatina');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0821, 08, 'Conceição da Barra');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0822, 08, 'Conceição do Castelo');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0823, 08, 'Divino de São Lourenço');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0824, 08, 'Domingos Martins');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0825, 08, 'Dores do Rio Preto');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0826, 08, 'Ecoporanga');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0827, 08, 'Fundão');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0828, 08, 'Guaçuí');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0829, 08, 'Guarapari');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0830, 08, 'Ibatiba');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0831, 08, 'Ibiraçu');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0832, 08, 'Ibitirama');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0833, 08, 'Iconha');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0834, 08, 'Irupi');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0835, 08, 'Itaguaçu');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0836, 08, 'Itapemirim');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0837, 08, 'Itarana');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0838, 08, 'Iúna');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0839, 08, 'Jaguaré');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0840, 08, 'Jerônimo Monteiro');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0841, 08, 'João Neiva');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0842, 08, 'Laranja da Terra');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0843, 08, 'Linhares');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0844, 08, 'Mantenópolis');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0845, 08, 'Marataízes');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0846, 08, 'Marechal Floriano');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0847, 08, 'Marilândia');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0848, 08, 'Mimoso do Sul');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0849, 08, 'Montanha');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0850, 08, 'Mucurici');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0851, 08, 'Muniz Freire');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0852, 08, 'Muqui');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0853, 08, 'Nova Venécia');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0854, 08, 'Pancas');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0855, 08, 'Pedro Canário');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0856, 08, 'Pinheiros');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0857, 08, 'Piúma');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0858, 08, 'Ponto Belo');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0859, 08, 'Presidente Kennedy');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0860, 08, 'Rio Bananal');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0861, 08, 'Rio Novo do Sul');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0862, 08, 'Santa Leopoldina');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0863, 08, 'Santa Maria de Jetibá');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0864, 08, 'Santa Teresa');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0865, 08, 'São Domingos do Norte');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0866, 08, 'São Gabriel da Palha');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0867, 08, 'São José do Calçado');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0868, 08, 'São Mateus');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0869, 08, 'São Roque do Canaã');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0870, 08, 'Serra');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0871, 08, 'Sooretama');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0872, 08, 'Vargem Alta');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0873, 08, 'Venda Nova do Imigrante');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0874, 08, 'Viana');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0875, 08, 'Vila Pavão');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0876, 08, 'Vila Valério');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0877, 08, 'Vila Velha');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (0878, 08, 'Vitória');


/* ********************** R i o   d e   J a n e i r o *********************** */

INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3565, 19, 'Angra dos Reis');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3566, 19, 'Aperibé');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3567, 19, 'Araruama');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3568, 19, 'Areal');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3569, 19, 'Armação dos Búzios');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3570, 19, 'Arraial do Cabo');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3571, 19, 'Barra do Piraí');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3572, 19, 'Barra Mansa');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3573, 19, 'Belford Roxo');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3574, 19, 'Bom Jardim');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3575, 19, 'Bom Jesus do Itabapoana');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3576, 19, 'Cabo Frio');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3577, 19, 'Cachoeiras de Macacu');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3578, 19, 'Cambuci');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3579, 19, 'Campos dos Goytacazes');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3580, 19, 'Cantagalo');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3581, 19, 'Carapebus');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3582, 19, 'Cardoso Moreira');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3583, 19, 'Carmo');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3584, 19, 'Casimiro de Abreu');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3585, 19, 'Comendador Levy Gasparian');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3586, 19, 'Conceição de Macabu');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3587, 19, 'Cordeiro');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3588, 19, 'Duas Barras');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3589, 19, 'Duque de Caxias');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3590, 19, 'Engenheiro Paulo de Frontin');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3591, 19, 'Guapimirim');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3592, 19, 'Iguaba Grande');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3593, 19, 'Itaboraí');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3594, 19, 'Itaguaí');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3595, 19, 'Italva');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3596, 19, 'Itaocara');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3597, 19, 'Itaperuna');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3598, 19, 'Itatiaia');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3599, 19, 'Japeri');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3600, 19, 'Laje do Muriaé');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3601, 19, 'Macaé');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3602, 19, 'Macuco');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3603, 19, 'Magé');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3604, 19, 'Mangaratiba');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3605, 19, 'Maricá');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3606, 19, 'Mendes');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3607, 19, 'Miguel Pereira');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3608, 19, 'Miracema');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3609, 19, 'Natividade');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3610, 19, 'Nilópolis');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3611, 19, 'Niterói');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3612, 19, 'Nova Friburgo');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3613, 19, 'Nova Iguaçu');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3614, 19, 'Paracambi');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3615, 19, 'Paraíba do Sul');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3616, 19, 'Parati');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3617, 19, 'Paty do Alferes');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3618, 19, 'Petrópolis');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3619, 19, 'Pinheiral');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3620, 19, 'Piraí');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3621, 19, 'Porciúncula');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3622, 19, 'Porto Real');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3623, 19, 'Quatis');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3624, 19, 'Queimados');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3625, 19, 'Quissamã');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3626, 19, 'Resende');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3627, 19, 'Rio Bonito');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3628, 19, 'Rio Claro');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3629, 19, 'Rio das Flores');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3630, 19, 'Rio das Ostras');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3631, 19, 'Rio de Janeiro');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3632, 19, 'Santa Maria Madalena');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3633, 19, 'Santo Antônio de Pádua');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3634, 19, 'São Fidélis');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3635, 19, 'São Francisco de Itabapoana');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3636, 19, 'São Gonçalo');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3637, 19, 'São João da Barra');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3638, 19, 'São João de Meriti');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3639, 19, 'São José de Ubá');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3640, 19, 'São José do Vale do Rio Preto');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3641, 19, 'São Pedro da Aldeia');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3642, 19, 'São Sebastião do Alto');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3643, 19, 'Sapucaia');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3644, 19, 'Saquarema');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3645, 19, 'Seropédica');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3646, 19, 'Silva Jardim');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3647, 19, 'Sumidouro');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3648, 19, 'Tanguá');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3649, 19, 'Teresópolis');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3650, 19, 'Trajano de Morais');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3651, 19, 'Três Rios');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3652, 19, 'Valença');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3653, 19, 'Varre-Sai');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3654, 19, 'Vassouras');
INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (3655, 19, 'Volta Redonda');

INSERT INTO omapp4.cidade (id, estado_id, nome) VALUES (9999, 28, 'Não Cadastrada');


insert into omapp4.usuario (id, login, senha) value(1,'jose@mail.com', '$2a$10$ovb822BNdtoHlQCyKFMMcuFhTIsa2a0DdO6.k.x2VDzxvqerJUTCy');

insert into omapp4.pessoa (id, usuario_id, nome, endereco_cidade_id, data_cadastro, data_atualizacao) value(1,1,'Jose',9999,'2020-12-22 21:05:49','2021-01-22 02:23:46.07' );

insert into omapp4.pessoa_fisica (id) values(1);

insert into omapp4.pessoa_grupo (pessoa_id, grupo_id) values(1,1);

insert into omapp4.imposto (id, nome, percentual) values(1,'ICMS',10);
insert into omapp4.imposto (id, nome, percentual) values(2,'ISS',7);