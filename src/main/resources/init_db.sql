CREATE SCHEMA `taxi_service` DEFAULT CHARACTER SET utf8;

CREATE TABLE `taxi_service`.`manufacturer`
(
    `id`      BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(225) NOT NULL,
    `country` VARCHAR(225) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `taxi_service`.`manufacturer` (`name`, `country`)
VALUES ('ZAZ', 'UA');

INSERT INTO `taxi_service`.`manufacturer` (`name`, `country`)
VALUES ('Skoda', 'PL');

ALTER TABLE `taxi_service`.`manufacturer`
    ADD COLUMN `deleted` TINYINT NOT NULL AFTER `country`;

CREATE TABLE `taxi_service`.`drivers` (
                                          `id` BIGINT NOT NULL AUTO_INCREMENT,
                                          `name_driver` VARCHAR(225) NOT NULL,
                                          `licence_number` VARCHAR(225) NOT NULL,
                                          `deleted` TINYINT NOT NULL DEFAULT 0,
                                          PRIMARY KEY (`id`),
                                          UNIQUE INDEX `licence_number_UNIQUE` (`licence_number` ASC) VISIBLE)
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

CREATE TABLE `taxi_service`.`cars` (
                                       `id` BIGINT NOT NULL AUTO_INCREMENT,
                                       `model` VARCHAR(225) NOT NULL,
                                       `manufacturer_id` BIGINT NOT NULL,
                                       `deleted` TINYINT NOT NULL DEFAULT 0,
                                       PRIMARY KEY (`id`),
                                       INDEX `car_manufacturer_fk_idx` (`manufacturer_id` ASC) VISIBLE,
                                       CONSTRAINT `car_manufacturer_fk`
                                           FOREIGN KEY (`manufacturer_id`)
                                               REFERENCES `taxi_service`.`manufacturer` (`id`)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION)
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

CREATE TABLE `taxi_service`.`cars_drivers` (
                                                 `id_car` BIGINT NOT NULL,
                                                 `id_driver` BIGINT NOT NULL,
                                                 INDEX `id_car_idx` (`id_car` ASC) VISIBLE,
                                                 INDEX `id_driver_idx` (`id_driver` ASC) VISIBLE,
                                                 CONSTRAINT `id_car`
                                                     FOREIGN KEY (`id_car`)
                                                         REFERENCES `taxi_service`.`cars` (`id`)
                                                         ON DELETE NO ACTION
                                                         ON UPDATE NO ACTION,
                                                 CONSTRAINT `id_driver`
                                                     FOREIGN KEY (`id_driver`)
                                                         REFERENCES `taxi_service`.`drivers` (`id`)
                                                         ON DELETE NO ACTION
                                                         ON UPDATE NO ACTION)
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

INSERT INTO `taxi_service`.`drivers` (`name`, `licence_number`) VALUES ('Bob', '777');
INSERT INTO `taxi_service`.`drivers` (`name`, `licence_number`) VALUES ('Alice', '666');

INSERT INTO `taxi_service`.`cars` (`model`, `manufacturer_id`) VALUES ('Fafia', '1');
INSERT INTO `taxi_service`.`cars` (`model`, `manufacturer_id`) VALUES ('Lanos', '2');



