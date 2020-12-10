-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema amt_project_01
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `amt_project_01` ;

-- -----------------------------------------------------
-- Schema amt_project_01
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `amt_project_01` DEFAULT CHARACTER SET utf8 ;
USE `amt_project_01` ;

-- -----------------------------------------------------
-- Table `amt_project_01`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amt_project_01`.`User` ;

CREATE TABLE IF NOT EXISTS `amt_project_01`.`User` (
  `idUser` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL UNIQUE,
  `lastname` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `idUser_UNIQUE` ON `amt_project_01`.`User` (`idUser` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `amt_project_01`.`Question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amt_project_01`.`Question` ;

CREATE TABLE IF NOT EXISTS `amt_project_01`.`Question` (
  `idQuestion` VARCHAR(255) NOT NULL,
  `text` VARCHAR(500) NOT NULL,
  `author` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idQuestion`),
  CONSTRAINT `fk_Question_User1`
    FOREIGN KEY (`author`)
    REFERENCES `amt_project_01`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `idQuestion_UNIQUE` ON `amt_project_01`.`Question` (`idQuestion` ASC) VISIBLE;

CREATE INDEX `fk_Question_User1_idx` ON `amt_project_01`.`Question` (`author` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `amt_project_01`.`Answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amt_project_01`.`Answer` ;

CREATE TABLE IF NOT EXISTS `amt_project_01`.`Answer` (
  `idAnswer` VARCHAR(255) NOT NULL,
  `text` VARCHAR(400) NOT NULL,
  `idQuestion` VARCHAR(255) NOT NULL,
  `idUser` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idAnswer`),
  CONSTRAINT `fk_Answer_Question1`
    FOREIGN KEY (`idQuestion`)
    REFERENCES `amt_project_01`.`Question` (`idQuestion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE ,
  CONSTRAINT `fk_Answer_User1`
    FOREIGN KEY (`idUser`)
    REFERENCES `amt_project_01`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `idAnswer_UNIQUE` ON `amt_project_01`.`Answer` (`idAnswer` ASC) VISIBLE;

CREATE INDEX `fk_Answer_Question1_idx` ON `amt_project_01`.`Answer` (`idQuestion` ASC) VISIBLE;

CREATE INDEX `fk_Answer_User1_idx` ON `amt_project_01`.`Answer` (`idUser` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `amt_project_01`.`User_votes_for_Question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amt_project_01`.`User_votes_for_Question` ;

CREATE TABLE IF NOT EXISTS `amt_project_01`.`User_votes_for_Question` (
  `idUser` VARCHAR(255) NOT NULL,
  `idQuestion` VARCHAR(255) NOT NULL,
  `isUpvote` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`idUser`, `idQuestion`),
  CONSTRAINT `fk_User_has_Question_User`
    FOREIGN KEY (`idUser`)
    REFERENCES `amt_project_01`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE ,
  CONSTRAINT `fk_User_has_Question_Question1`
    FOREIGN KEY (`idQuestion`)
    REFERENCES `amt_project_01`.`Question` (`idQuestion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE )
ENGINE = InnoDB;

CREATE INDEX `fk_User_has_Question_Question1_idx` ON `amt_project_01`.`User_votes_for_Question` (`idQuestion` ASC) VISIBLE;

CREATE INDEX `fk_User_has_Question_User_idx` ON `amt_project_01`.`User_votes_for_Question` (`idUser` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `amt_project_01`.`User_votes_for_Answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amt_project_01`.`User_votes_for_Answer` ;

CREATE TABLE IF NOT EXISTS `amt_project_01`.`User_votes_for_Answer` (
  `idUser` VARCHAR(255) NOT NULL,
  `idAnswer` VARCHAR(255) NOT NULL,
  `isUpvote` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`idUser`, `idAnswer`),
  CONSTRAINT `fk_User_has_Answer_User1`
    FOREIGN KEY (`idUser`)
    REFERENCES `amt_project_01`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE ,
  CONSTRAINT `fk_User_has_Answer_Answer1`
    FOREIGN KEY (`idAnswer`)
    REFERENCES `amt_project_01`.`Answer` (`idAnswer`)
    ON DELETE CASCADE
    ON UPDATE CASCADE )
ENGINE = InnoDB;

CREATE INDEX `fk_User_has_Answer_Answer1_idx` ON `amt_project_01`.`User_votes_for_Answer` (`idAnswer` ASC) VISIBLE;

CREATE INDEX `fk_User_has_Answer_User1_idx` ON `amt_project_01`.`User_votes_for_Answer` (`idUser` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `amt_project_01`.`User_comments_Question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amt_project_01`.`User_comments_Question` ;

CREATE TABLE IF NOT EXISTS `amt_project_01`.`User_comments_Question` (
  `idUser` VARCHAR(255) NOT NULL,
  `idQuestion` VARCHAR(255) NOT NULL,
  `comment` VARCHAR(500) NOT NULL,
  `idComment` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idUser`, `idQuestion`, `idComment`),
  CONSTRAINT `fk_User_has_Question_User0`
    FOREIGN KEY (`idUser`)
    REFERENCES `amt_project_01`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE ,
  CONSTRAINT `fk_User_has_Question_Question10`
    FOREIGN KEY (`idQuestion`)
    REFERENCES `amt_project_01`.`Question` (`idQuestion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE )
ENGINE = InnoDB;

CREATE INDEX `fk_User_has_Question_Question1_idx` ON `amt_project_01`.`User_comments_Question` (`idQuestion` ASC) VISIBLE;

CREATE INDEX `fk_User_has_Question_User_idx` ON `amt_project_01`.`User_comments_Question` (`idUser` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `amt_project_01`.`User_comments_Answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amt_project_01`.`User_comments_Answer` ;

CREATE TABLE IF NOT EXISTS `amt_project_01`.`User_comments_Answer` (
  `idUser` VARCHAR(255) NOT NULL,
  `idAnswer` VARCHAR(255) NOT NULL,
  `comment` VARCHAR(500) NOT NULL,
  `idComment` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idUser`, `idAnswer`, `idComment`),
  CONSTRAINT `fk_User_has_Answer_User10`
    FOREIGN KEY (`idUser`)
    REFERENCES `amt_project_01`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE ,
  CONSTRAINT `fk_User_has_Answer_Answer10`
    FOREIGN KEY (`idAnswer`)
    REFERENCES `amt_project_01`.`Answer` (`idAnswer`)
    ON DELETE CASCADE
    ON UPDATE CASCADE )
ENGINE = InnoDB;

CREATE INDEX `fk_User_has_Answer_Answer1_idx` ON `amt_project_01`.`User_comments_Answer` (`idAnswer` ASC) VISIBLE;

CREATE INDEX `fk_User_has_Answer_User1_idx` ON `amt_project_01`.`User_comments_Answer` (`idUser` ASC) VISIBLE;

CREATE UNIQUE INDEX `idComment_UNIQUE` ON `amt_project_01`.`User_comments_Answer` (`idComment` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
