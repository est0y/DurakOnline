CREATE TABLE games (
    id character varying(255) NOT NULL primary key,
    game_status smallint,
    pass_count integer NOT NULL,
    playing_table jsonb,
    attacker_seat_id bigint,
    defender_seat_id bigint
);

CREATE TABLE players (
    id character varying(255) NOT NULL primary key,
    name character varying(255)
);

CREATE TABLE seats (
    id bigserial NOT NULL primary key,
    cards_id integer[],
    is_free boolean NOT NULL,
    number integer NOT NULL,
    player_id character varying(255) references players(id),
    game_id character varying(255) references games(id)
);