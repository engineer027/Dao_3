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