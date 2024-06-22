DO
$$
    DECLARE
        sequences TEXT[] := ARRAY ['client_seq', 'common_object_seq', 'contact_seq', 'country_seq', 'location_seq', 'person_seq', 'region_seq', 'role_seq', 'supplier_seq', 'user_seq', 'vehicle_hire_seq', 'vehicle_movement_seq', 'vehicle_seq'];
        seq_name  TEXT;
    BEGIN
        FOREACH seq_name IN ARRAY sequences
            LOOP
                IF NOT EXISTS(
                        SELECT 1
                        FROM information_schema.sequences
                        WHERE sequence_name = seq_name
                    ) THEN
                    -- Для role_seq устанавливаем начальное значение и шаг в соответствии с требованиями
                    IF seq_name = 'role_seq' THEN
                        EXECUTE format('CREATE SEQUENCE %I START WITH 1 INCREMENT BY 10;', seq_name);
                    ELSE
                        EXECUTE format('CREATE SEQUENCE %I START WITH 10 INCREMENT BY 50;', seq_name);
                    END IF;
                    RAISE NOTICE 'Sequence % created.', seq_name;
                ELSE
                    RAISE NOTICE 'Sequence % already exists.', seq_name;
                END IF;
            END LOOP;
    END
$$;

create table client
(
    countryid          bigint,
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    regionid           bigint,
    address            varchar(255),
    city               varchar(255),
    created_by         varchar(255) not null,
    details            varchar(255),
    email              varchar(255),
    last_modified_by   varchar(255),
    name               varchar(255),
    phone              varchar(255),
    website            varchar(255),
    primary key (id)
);
create table contact
(
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    created_by         varchar(255) not null,
    email              varchar(255),
    firstname          varchar(255),
    last_modified_by   varchar(255),
    lastname           varchar(255),
    phone              varchar(255),
    remarks            varchar(255),
    primary key (id)
);
create table country
(
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    capital            varchar(255),
    code               varchar(255),
    continent          varchar(255),
    created_by         varchar(255) not null,
    description        varchar(255) unique,
    last_modified_by   varchar(255),
    primary key (id)
);
create table employee
(
    countryid          bigint,
    created_date       timestamp(6) not null,
    date_of_birth      timestamp(6),
    employeetypeid     bigint,
    hire_date          timestamp(6),
    id                 bigint       not null,
    jobtitleid         bigint,
    last_modified_date timestamp(6),
    regionid           bigint,
    userid             bigint,
    address            varchar(255),
    city               varchar(255),
    created_by         varchar(255) not null,
    email              varchar(255),
    firstname          varchar(255),
    gender             varchar(255),
    last_modified_by   varchar(255),
    lastname           varchar(255),
    marital_status     varchar(255),
    othername          varchar(255),
    passport           varchar(255) unique,
    phone              varchar(255),
    photo              varchar(255),
    primary key (id)
);
create table employee_status
(
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    created_by         varchar(255) not null,
    description        varchar(255),
    details            varchar(255),
    last_modified_by   varchar(255),
    primary key (id)
);
create table employee_type
(
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    created_by         varchar(255) not null,
    description        varchar(255),
    details            varchar(255),
    last_modified_by   varchar(255),
    primary key (id)
);
create table invoice
(
    clientid        integer,
    id              serial not null,
    invoicestatusid integer,
    invoice_date    timestamp(6),
    remarks         varchar(255),
    primary key (id)
);
create table invoice_status
(
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    created_by         varchar(255) not null,
    description        varchar(255),
    details            varchar(255),
    last_modified_by   varchar(255),
    primary key (id)
);
create table job_title
(
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    created_by         varchar(255) not null,
    description        varchar(255),
    details            varchar(255),
    last_modified_by   varchar(255),
    primary key (id)
);
create table location
(
    countryid          bigint,
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    regionid           bigint,
    address            varchar(255),
    city               varchar(255),
    created_by         varchar(255) not null,
    description        varchar(255),
    details            varchar(255),
    last_modified_by   varchar(255),
    primary key (id)
);
create table module
(
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    created_by         varchar(255) not null,
    description        varchar(255),
    details            varchar(255),
    last_modified_by   varchar(255),
    primary key (id)
);
create table region
(
    countryid          bigint,
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    capital            varchar(255),
    code               varchar(255),
    created_by         varchar(255) not null,
    details            varchar(255),
    last_modified_by   varchar(255),
    name               varchar(255),
    primary key (id)
);
create table role
(
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    created_by         varchar(255) not null,
    description        varchar(255),
    details            varchar(255),
    last_modified_by   varchar(255),
    primary key (id)
);
create table supplier
(
    countryid          bigint,
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    regionid           bigint,
    address            varchar(255),
    city               varchar(255),
    created_by         varchar(255) not null,
    details            varchar(255),
    email              varchar(255),
    last_modified_by   varchar(255),
    name               varchar(255),
    phone              varchar(255),
    website            varchar(255),
    primary key (id)
);
create table user_role
(
    role_id bigint not null,
    user_id bigint not null,
    primary key (role_id, user_id)
);
create table users
(
    id       bigint              not null,
    email    varchar(255) unique not null,
    password varchar(255)        not null,
    username varchar(255) unique not null,
    primary key (id)
);
create table vehicle
(
    employeeid         integer,
    id                 integer      not null,
    locationid         integer,
    vehiclemakeid      integer,
    vehiclemodelid     integer,
    vehiclestatusid    integer,
    vehicletypeid      integer,
    acquisition_date   timestamp(6),
    created_date       timestamp(6) not null,
    last_modified_date timestamp(6),
    registration_date  timestamp(6),
    created_by         varchar(255) not null,
    description        varchar(255),
    fuel_capacity      varchar(255),
    last_modified_by   varchar(255),
    name               varchar(255),
    net_weight         varchar(255),
    photo              varchar(255),
    power              varchar(255),
    remarks            varchar(255),
    vehicle_number     varchar(255) unique,
    primary key (id)
);
create table vehicle_hire
(
    clientid           bigint,
    created_date       timestamp(6) not null,
    date_in            timestamp(6),
    date_out           timestamp(6),
    id                 bigint       not null,
    last_modified_date timestamp(6),
    locationid         bigint,
    vehicleid          bigint,
    created_by         varchar(255) not null,
    last_modified_by   varchar(255),
    price              varchar(255),
    remarks            varchar(255),
    time_in            varchar(255),
    time_out           varchar(255),
    primary key (id)
);
create table vehicle_maintenance
(
    created_date       timestamp(6) not null,
    end_date           timestamp(6),
    id                 bigserial    not null,
    last_modified_date timestamp(6),
    start_date         timestamp(6),
    supplierid         bigint,
    vehicleid          bigint,
    created_by         varchar(255) not null,
    last_modified_by   varchar(255),
    price              varchar(255),
    remarks            varchar(255),
    primary key (id)
);
create table vehicle_make
(
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    created_by         varchar(255) not null,
    description        varchar(255),
    details            varchar(255),
    last_modified_by   varchar(255),
    primary key (id)
);
create table vehicle_model
(
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    vehiclemakeid      bigint,
    created_by         varchar(255) not null,
    description        varchar(255),
    details            varchar(255),
    last_modified_by   varchar(255),
    primary key (id)
);
create table vehicle_movement
(
    created_date       timestamp(6) not null,
    date1              timestamp(6),
    date2              timestamp(6),
    id                 bigint       not null,
    last_modified_date timestamp(6),
    locationid1        bigint,
    locationid2        bigint,
    vehicleid          bigint,
    created_by         varchar(255) not null,
    last_modified_by   varchar(255),
    remarks            varchar(255),
    primary key (id)
);
create table vehicle_status
(
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    created_by         varchar(255) not null,
    description        varchar(255),
    details            varchar(255),
    last_modified_by   varchar(255),
    primary key (id)
);
create table vehicle_type
(
    created_date       timestamp(6) not null,
    id                 bigint       not null,
    last_modified_date timestamp(6),
    created_by         varchar(255) not null,
    description        varchar(255),
    details            varchar(255),
    last_modified_by   varchar(255),
    primary key (id)
);
alter table if exists client
    add constraint FKdl2uu6vavuggmw39eqog9ka84 foreign key (countryid) references country;
alter table if exists client
    add constraint FKip2cv5sikr6nvvfxqao5vhmti foreign key (regionid) references region;
alter table if exists employee
    add constraint FK9hga8iyrt1lh8ofyic3lb0792 foreign key (countryid) references country;
alter table if exists employee
    add constraint FK94k4dqfc48kdmbkeba0gjlddx foreign key (regionid) references region;
alter table if exists employee
    add constraint FKe1h4f2i4kb3hdr67guoeyg16h foreign key (employeetypeid) references employee_type;
alter table if exists employee
    add constraint FKod9tsamp0p54ko9vk9fwusrig foreign key (jobtitleid) references job_title;
alter table if exists employee
    add constraint FK1h3eh0eqywbnb6rjhkualu3h6 foreign key (userid) references users;
alter table if exists invoice
    add constraint FK2c5k5upd3h0m0ymktk0wchenq foreign key (clientid) references client;
alter table if exists invoice
    add constraint FKjk36nvo550w83beam2qtveakd foreign key (invoicestatusid) references invoice_status;
alter table if exists location
    add constraint FKs7swryq3x80exyw5x7q9exk4 foreign key (countryid) references country;
alter table if exists location
    add constraint FKsx0cej6kkbptambffskkuduqs foreign key (regionid) references region;
alter table if exists region
    add constraint FK9pva4aa7u4qe113nbsvdoj5yq foreign key (countryid) references country;
alter table if exists supplier
    add constraint FKan3bwr38w0wfolb0s19xdh9xi foreign key (countryid) references country;
alter table if exists supplier
    add constraint FK64kjilfuckyxl5axquphl3i4o foreign key (regionid) references region;
alter table if exists user_role
    add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role;
alter table if exists user_role
    add constraint FKj345gk1bovqvfame88rcx7yyx foreign key (user_id) references users;
alter table if exists vehicle
    add constraint FKmuvyuwi9yyjqk0cub1e7vk43v foreign key (locationid) references location;
alter table if exists vehicle
    add constraint FK25tg0hy1caukt3x859a275527 foreign key (employeeid) references employee;
alter table if exists vehicle
    add constraint FK5lbolyg4qc0vcr12yodm5ubdh foreign key (vehiclemakeid) references vehicle_make;
alter table if exists vehicle
    add constraint FKshu1mt0nfkb4xlch4t5qvogfn foreign key (vehiclemodelid) references vehicle_model;
alter table if exists vehicle
    add constraint FK1q3s6m4olag2pdx65t4d42079 foreign key (vehiclestatusid) references vehicle_status;
alter table if exists vehicle
    add constraint FKmti0lsn25daryo8y745ufjx3u foreign key (vehicletypeid) references vehicle_type;
alter table if exists vehicle_hire
    add constraint FKlj9e63xyvpfnsbh7j9xmp6fml foreign key (clientid) references client;
alter table if exists vehicle_hire
    add constraint FK2ycyc36lgc6locf51iyv5myet foreign key (locationid) references location;
alter table if exists vehicle_hire
    add constraint FKp28dbwi3abrf3miyyxkry9wbu foreign key (vehicleid) references vehicle;
alter table if exists vehicle_maintenance
    add constraint FKqf2m3nwqkrfqsoj9759fom10x foreign key (supplierid) references supplier;
alter table if exists vehicle_maintenance
    add constraint FK27c7ggyo6wo7thsmlku6dwa50 foreign key (vehicleid) references vehicle;
alter table if exists vehicle_model
    add constraint FK4ertu0q10tecqpt4g0ra8nht9 foreign key (vehiclemakeid) references vehicle_make;
alter table if exists vehicle_movement
    add constraint FK94ggdhphxysfso6iy5xpft19r foreign key (locationid1) references location;
alter table if exists vehicle_movement
    add constraint FKbot8bs9msjkcjss6bjyqny7sy foreign key (locationid2) references location;
alter table if exists vehicle_movement
    add constraint FKoo5sc3l8tyvyroo4yrx9ptjng foreign key (vehicleid) references vehicle;