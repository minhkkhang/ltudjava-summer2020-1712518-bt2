-- MySQL Workbench Forward Engineering
alter user 'root'@'localhost' identified with mysql_native_password by 'root';
flush privileges;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema quanlysinhvien
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema quanlysinhvien
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `quanlysinhvien` DEFAULT CHARACTER SET utf8 ;
USE `quanlysinhvien` ;

-- -----------------------------------------------------
-- Table `quanlysinhvien`.`Lop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysinhvien`.`Lop` (
  `MaLop` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`MaLop`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quanlysinhvien`.`SinhVien`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysinhvien`.`SinhVien` (
  `MSSV` INT NOT NULL AUTO_INCREMENT,
  `HoTen` NVARCHAR(50) NOT NULL,
  `GioiTinh` TINYINT NOT NULL,
  `CMND` BIGINT(15) NOT NULL,
  `MaLop` VARCHAR(10) NOT NULL,
  `MatKhau` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`MSSV`),
  INDEX `fk_SinhVien_Lop_idx` (`MaLop` ASC) VISIBLE,
  CONSTRAINT `fk_SinhVien_Lop`
    FOREIGN KEY (`MaLop`)
    REFERENCES `quanlysinhvien`.`Lop` (`MaLop`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quanlysinhvien`.`MonHoc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysinhvien`.`MonHoc` (
  `MaMon` VARCHAR(10) NOT NULL,
  `TenMon` NVARCHAR(50) NOT NULL,
  PRIMARY KEY (`MaMon`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quanlysinhvien`.`HocPhan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysinhvien`.`HocPhan` (
  `MaHocPhan` VARCHAR(20) NOT NULL,
  `MaLop` VARCHAR(10) NOT NULL,
  `MaMon` VARCHAR(10) NOT NULL,
  `PhongHoc` VARCHAR(5) NULL,
  PRIMARY KEY (`MaHocPhan`),
  INDEX `fk_HocPhan_Lop1_idx` (`MaLop` ASC) VISIBLE,
  INDEX `fk_HocPhan_MonHoc1_idx` (`MaMon` ASC) VISIBLE,
  CONSTRAINT `fk_HocPhan_Lop1`
    FOREIGN KEY (`MaLop`)
    REFERENCES `quanlysinhvien`.`Lop` (`MaLop`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HocPhan_MonHoc1`
    FOREIGN KEY (`MaMon`)
    REFERENCES `quanlysinhvien`.`MonHoc` (`MaMon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quanlysinhvien`.`HocLop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysinhvien`.`HocLop` (
  `MSSV` INT NOT NULL,
  `MaHocPhan` VARCHAR(20) NOT NULL,
  `DiemGK` FLOAT NULL,
  `DiemCK` FLOAT NULL,
  `DiemKhac` FLOAT NULL,
  `DiemTong` FLOAT NULL,
  PRIMARY KEY (`MSSV`, `MaHocPhan`),
  INDEX `fk_HocLop_HocPhan1_idx` (`MaHocPhan` ASC) VISIBLE,
  CONSTRAINT `fk_HocLop_SinhVien1`
    FOREIGN KEY (`MSSV`)
    REFERENCES `quanlysinhvien`.`SinhVien` (`MSSV`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HocLop_HocPhan1`
    FOREIGN KEY (`MaHocPhan`)
    REFERENCES `quanlysinhvien`.`HocPhan` (`MaHocPhan`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quanlysinhvien`.`PhucKhao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysinhvien`.`PhucKhao` (
  `CotDiem` SMALLINT(4) NULL,
  `DiemMongMuon` FLOAT NULL,
  `LyDo` NVARCHAR(150) NULL,
  `MaMon` VARCHAR(10) NOT NULL,
  `TinhTrang` SMALLINT(3) NOT NULL,
  `MSSV` INT NOT NULL,
  `STT` INT NOT NULL,
  INDEX `fk_PhucKhao_MonHoc1_idx` (`MaMon` ASC) VISIBLE,
  INDEX `fk_PhucKhao_SinhVien1_idx` (`MSSV` ASC) VISIBLE,
  PRIMARY KEY (`STT`),
  CONSTRAINT `fk_PhucKhao_MonHoc1`
    FOREIGN KEY (`MaMon`)
    REFERENCES `quanlysinhvien`.`MonHoc` (`MaMon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PhucKhao_SinhVien1`
    FOREIGN KEY (`MSSV`)
    REFERENCES `quanlysinhvien`.`SinhVien` (`MSSV`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quanlysinhvien`.`GiaoVu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysinhvien`.`GiaoVu` (
  `TenGV` VARCHAR(10) NOT NULL,
  `MatKhau` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`TenGV`))
ENGINE = InnoDB;
insert into GiaoVu values ("giaovu","giaovu");

-- -----------------------------------------------------
-- Table `quanlysinhvien`.`MoPhucKhao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysinhvien`.`MoPhucKhao` (
  `DotPhucKhao` VARCHAR(6) NOT NULL,
  `NgayBatDau` VARCHAR(11) NULL,
  `NgayKetThuc` VARCHAR(11) NULL,
  PRIMARY KEY (`DotPhucKhao`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
