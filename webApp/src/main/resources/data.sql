-- Active: 1622622429103@@127.0.0.1@3306@kiss-luler-back
INSERT INTO
  `user`(
    id,
    email,
    first_name,
    last_name,
    password,
    role,
    birthdate,
    job,
    photo,
    pseudo,
    subscribe_date
  )
VALUES
  (
    1,
    'j.jean@gmail.com',
    'Jean',
    'Paul',
    '$2a$10$fGpvN77P4H89ddPMxVL5burWrQqKlTiaxUcOpaLfyNkPn3JyMt.26',
    'USER',
    '1988-07-01',
    NULL,
    NULL,
    NULL,
    '2022-04-02'
  ),(
    2,
    'juju@gmail.com',
    'Jules',
    'Dupond',
    '$2a$10$cKMOTutNGUXXPEUT3VQYtuQTu/Z/fd2D2Ig.m5rZOi3gpTkc3UL0S',
    'USER',
    '1988-07-01',
    NULL,
    NULL,
    NULL,
    '2022-04-02'
  ),(
    3,
    'beberd@gmail.com',
    'Bernard',
    'Legrand',
    '$2a$10$L73pgnWKBqRiR8Utn38KL.ElRjMTH.tT.Qbs7o2Yj0uewzetkQ6v6',
    'USER',
    '1988-07-01',
    NULL,
    NULL,
    NULL,
    '2022-04-02'
  ),(
    4,
    'admin@gmail.com',
    'admin',
    'admin',
    '$2a$10$avJXsAy4aIs6UTGmft6vu.x7hoOthRf2/3BHkIQTGP/mIxjPiKrhS',
    'USER',
    '1988-07-01',
    NULL,
    NULL,
    NULL,
    '2022-04-02'
  );
INSERT INTO
  `project`(
    id,
    category,
    date_end,
    date_init,
    description,
    name,
    photo,
    status,
    user_id,
    amount_init
  )
VALUES
  (
    1,
    'TOURDATE',
    '2022-09-10',
    '2022-06-10',
    'Ceci est un concert remarquable, familles, amis, venez comme vous êtes et surtout avec de l argent',
    'Concert rock',
    NULL,
    'INPROGRESS',
    1,
    500
  ),(
    2,
    'CD',
    '2022-10-06',
    '2022-06-12',
    'Je diffuse mon premier CD, il est détente et harmonieux, venez l ecouter et donnez moi du flouz',
    'CD Indé',
    NULL,
    'INPROGRESS',
    1,
    300
  ),(
    3,
    'EP',
    '2022-07-05',
    '2022-05-10',
    'Nous sommes un groupe tendance neo folk, du indochine version maé quoi, investissez',
    'Un EP qui va faire un carton',
    NULL,
    'CONCEPTION',
    2,
    24
  );
INSERT INTO
  `consideration`(
    id,
    consid_amount,
    description,
    photo,
    title,
    project_id,
    status
  )
VALUES
  (
    1,
    100,
    NULL,
    'myFile',
    'une photo',
    2,
    'READY'
  ),
  (
    2,
    20,
    'Une magnifique photo de concert vous sera envoyé, elle représentera toutes les Paul Godess de l avenir',
    'myFile',
    'une photo',
    1,
    'INPROGRESS'
  ),(
    3,
    50,
    'Une place de concert privative de Justin Timberlake et Papa Pingouin en spécial guest',
    NULL,
    'Une place de concert',
    1,
    'READY'
  ),(4, 40, 'Le cd dédicacé de DIAMS alors laisse toi kiffer....', NULL, 'un CD dedicacé', 2, 'READY'),
  (
    5,
    30,
    'Alors là c est le pompono de la pomponette, la reconnaissance de Roro, foncez !!' ,
    NULL,
    'Ma reconnaissance éternelle',
    3,
    'READY'
  );
INSERT INTO
  `user_order`(
    id,
    amount,
    date,
    project_id,
    user_id,
    consideration_id
  )
VALUES
  (1, 20, '2022-06-20', 1, 1, 3),(2, 30, '2022-05-05', 2, 2, 1),(3, 50, '2022-05-05', 3, 3, 5);