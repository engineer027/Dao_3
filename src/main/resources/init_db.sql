CREATE SCHEMA `taxi_service` DEFAULT CHARACTER SET utf8;

CREATE TABLE `taxi_service`.`manufacturer`
(
    `idManufacturer`      BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `nameManufacturer`    VARCHAR(225) NOT NULL,
    `countryManufacturer` VARCHAR(225) NOT NULL,
    PRIMARY KEY (`idManufacturer`),
    UNIQUE INDEX `nameManufacturer_UNIQUE` (`nameManufacturer` ASC) VISIBLE
);

INSERT INTO `taxi_service`.`manufacturer` (`nameManufacturer`, `countryManufacturer`)
VALUES ('ZAZ', 'UA');

INSERT INTO `taxi_service`.`manufacturer` (`nameManufacturer`, `countryManufacturer`)
VALUES ('Skoda', 'PL');

ALTER TABLE `taxi_service`.`manufacturer`
    ADD COLUMN `deleteManufacturer` TINYINT NOT NULL AFTER `countryManufacturer`;

ALTER TABLE `taxi_service`.`manufacturer`
    CHANGE COLUMN `idManufacturer` `id_manufacturer` BIGINT NOT NULL AUTO_INCREMENT ,
    CHANGE COLUMN `nameManufacturer` `name_manufacturer` VARCHAR(225) NOT NULL ,
    CHANGE COLUMN `countryManufacturer` `country_manufacturer` VARCHAR(225) NOT NULL ,
    CHANGE COLUMN `deleteManufacturer` `delete_manufacturer` TINYINT NOT NULL DEFAULT '0' ;

CREATE TABLE `taxi_service`.`drivers` (
                                          `id_driver` BIGINT NOT NULL,
                                          `name_driver` VARCHAR(225) NOT NULL,
                                          `licence_number_driver` VARCHAR(225) NOT NULL,
                                          `delete_driver` TINYINT NOT NULL DEFAULT 0,
                                          PRIMARY KEY (`id_driver`),
                                          UNIQUE INDEX `name_driver_UNIQUE` (`name_driver` ASC) VISIBLE,
                                          UNIQUE INDEX `licence_number_driver_UNIQUE` (`licence_number_driver` ASC) VISIBLE)
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

ALTER TABLE `taxi_service`.`drivers`
    CHANGE COLUMN `id_driver` `id_driver` BIGINT NOT NULL AUTO_INCREMENT ;

CREATE TABLE `taxi_service`.`cars` (
                                       `id_car` BIGINT NOT NULL AUTO_INCREMENT,
                                       `model_car` VARCHAR(225) NOT NULL,
                                       `manufacturer_id` BIGINT NOT NULL,
                                       `delete_car` TINYINT NOT NULL DEFAULT 0,
                                       PRIMARY KEY (`id_car`),
                                       UNIQUE INDEX `carscol_UNIQUE` (`model_car` ASC) VISIBLE,
                                       INDEX `car_manufacturer_fk_idx` (`manufacturer_id` ASC) VISIBLE,
                                       CONSTRAINT `car_manufacturer_fk`
                                           FOREIGN KEY (`manufacturer_id`)
                                               REFERENCES `taxi_service`.`manufacturer` (`id_manufacturer`)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION)
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

CREATE TABLE `taxi_service`.`cars_drivers` (
                                                 `id_car` BIGINT NOT NULL,
                                                 `id_driver` BIGINT NOT NULL,
                                                 INDEX `id_car_idx` (`id_car` ASC) VISIBLE,
                                                 INDEX `id_driver_idx` (`id_driver` ASC) VISIBLE,
                                                 CONSTRAINT `id_car`
                                                     FOREIGN KEY (`id_car`)
                                                         REFERENCES `taxi_service`.`cars` (`id_car`)
                                                         ON DELETE NO ACTION
                                                         ON UPDATE NO ACTION,
                                                 CONSTRAINT `id_driver`
                                                     FOREIGN KEY (`id_driver`)
                                                         REFERENCES `taxi_service`.`drivers` (`id_driver`)
                                                         ON DELETE NO ACTION
                                                         ON UPDATE NO ACTION)
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

INSERT INTO `taxi_service`.`drivers` (`name_driver`, `licence_number_driver`) VALUES ('Bob', '777');
INSERT INTO `taxi_service`.`drivers` (`name_driver`, `licence_number_driver`) VALUES ('Alice', '666');

INSERT INTO `taxi_service`.`cars` (`model_car`, `manufacturer_id`) VALUES ('Fafia', '1');
INSERT INTO `taxi_service`.`cars` (`model_car`, `manufacturer_id`) VALUES ('Lanos', '2');

ALTER TABLE `taxi_service`.`cars`
    CHANGE COLUMN `delete_car` `delete_car` TINYINT NOT NULL DEFAULT '0' ;

ALTER TABLE `taxi_service`.`cars_drivers`
    RENAME TO  `taxi_service`.`cars_drivers` ;

