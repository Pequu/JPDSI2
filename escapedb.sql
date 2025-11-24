-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Lis 24, 2025 at 07:07 PM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `escapedb`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `accounts`
--

CREATE TABLE `accounts` (
  `idAccount` int(11) NOT NULL,
  `accName` varchar(25) NOT NULL,
  `accSurname` varchar(25) NOT NULL,
  `accBirthDate` date NOT NULL,
  `accIsActive` tinyint(1) NOT NULL,
  `accCreation` date NOT NULL,
  `accDeletion` date DEFAULT NULL,
  `accPass` varchar(255) NOT NULL,
  `accLogin` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`idAccount`, `accName`, `accSurname`, `accBirthDate`, `accIsActive`, `accCreation`, `accDeletion`, `accPass`, `accLogin`) VALUES
(1, 'administrator', '', '0000-00-00', 1, '2025-05-23', NULL, '$2y$10$g6.X0nr2RrUajDYCCbbqX..nQNLEqOuRe4uZ9us13N2FFEd/ucEBK', 'admin'),
(3, 'Mateusz', 'Rzymowski', '2002-09-27', 1, '2025-05-23', NULL, '$2y$10$qE8ueykvbsjBOVjDagvVYO96/7X7uVNwRvU/c6sBnQCyGT/iOS9DC', 'mati'),
(4, 'Adrian', 'Kuc', '2002-03-18', 1, '2025-05-23', NULL, '$2y$10$TmpCgmzn2wmFSV9PcJ3w5e8Tqm7gedBUQp68yrRThPTeK64IGK9I2', 'adrian'),
(10, 'Emilia', 'Dąbrowska', '2003-03-10', 1, '2025-05-29', NULL, '$2y$10$/.mdPF57oK71c98g/11QzeuTk1GmvNn/3EQee54dsrfwoe4IfuVSe', 'emson07'),
(11, 'Daniel', 'Dąbrowski', '2209-09-24', 1, '2025-05-29', NULL, '$2y$10$QJIWRl0JmoYGubezwAWM/eK25/fkyQl95bjv.h9AULlaG.0F4eJjC', 'danio'),
(12, 'test', 'test', '2003-02-22', 1, '2025-05-29', NULL, '$2y$10$BFPrXoaVCUcenxJiqmt13erLV.5kF8AEcYq6j2FG0oNk9gwS472eC', 'test');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `accroles`
--

CREATE TABLE `accroles` (
  `idAR` int(11) NOT NULL,
  `acc_idAccount` int(11) NOT NULL,
  `roles_idRole` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `accroles`
--

INSERT INTO `accroles` (`idAR`, `acc_idAccount`, `roles_idRole`) VALUES
(1, 1, 1),
(4, 4, 3),
(7, 3, 2),
(9, 10, 3),
(10, 11, 3),
(11, 12, 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `reservations`
--

CREATE TABLE `reservations` (
  `idReservation` int(11) NOT NULL,
  `resDate` date NOT NULL,
  `resPayment` int(11) DEFAULT NULL,
  `resPrice` int(11) NOT NULL,
  `resIsActive` tinyint(1) NOT NULL,
  `rooms_idRoom` int(11) NOT NULL,
  `vouchers_idVoucher` int(11) DEFAULT NULL,
  `accounts_idAccount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`idReservation`, `resDate`, `resPayment`, `resPrice`, `resIsActive`, `rooms_idRoom`, `vouchers_idVoucher`, `accounts_idAccount`) VALUES
(1, '2025-06-10', NULL, 99, 1, 4, NULL, 10),
(2, '2025-06-07', NULL, 129, 1, 3, NULL, 10),
(3, '2025-06-04', NULL, 99, 1, 4, NULL, 10),
(4, '2025-06-10', NULL, 89, 1, 2, NULL, 4),
(5, '2025-06-20', NULL, 99, 1, 4, NULL, 11),
(6, '2025-06-16', NULL, 89, 1, 2, NULL, 11),
(7, '2025-06-05', NULL, 79, 1, 1, NULL, 11),
(8, '2025-06-08', NULL, 129, 1, 3, NULL, 11),
(9, '2025-06-03', NULL, 79, 1, 1, NULL, 10),
(10, '2025-06-03', NULL, 89, 1, 2, NULL, 10),
(11, '2025-06-18', NULL, 99, 1, 4, NULL, 10),
(12, '2025-07-10', NULL, 89, 1, 4, 1, 10),
(13, '2025-07-18', NULL, 104, 1, 3, 2, 10),
(14, '2025-09-18', NULL, 96, 1, 3, 3, 10),
(15, '2025-06-13', NULL, 129, 1, 3, NULL, 10),
(16, '2025-06-03', 1, 79, 1, 1, NULL, 10),
(17, '2025-06-11', 2, 99, 1, 4, NULL, 11),
(18, '2025-06-08', 2, 79, 1, 1, NULL, 11),
(19, '2025-06-08', 1, 99, 1, 4, NULL, 11),
(20, '2025-06-01', 1, 29, 1, 1, 4, 11),
(21, '2025-06-25', 1, 79, 1, 3, 4, 11),
(22, '2025-06-02', 2, 9, 1, 4, 5, 10);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `roles`
--

CREATE TABLE `roles` (
  `idRole` int(11) NOT NULL,
  `roleName` varchar(25) NOT NULL,
  `roleIsActive` tinyint(1) NOT NULL,
  `roleCreation` date NOT NULL,
  `roleDeletion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`idRole`, `roleName`, `roleIsActive`, `roleCreation`, `roleDeletion`) VALUES
(1, 'admin', 1, '2025-05-25', NULL),
(2, 'pracownik', 1, '2025-05-25', NULL),
(3, 'klient', 1, '2025-05-25', NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rooms`
--

CREATE TABLE `rooms` (
  `idRoom` int(11) NOT NULL,
  `roomName` varchar(25) NOT NULL,
  `roomDescription` varchar(500) DEFAULT NULL,
  `roomPrice` int(11) NOT NULL,
  `roomCreation` date NOT NULL,
  `roomDeletion` date DEFAULT NULL,
  `roomIsActive` tinyint(1) NOT NULL,
  `roomCover` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`idRoom`, `roomName`, `roomDescription`, `roomPrice`, `roomCreation`, `roomDeletion`, `roomIsActive`, `roomCover`) VALUES
(1, 'Wrota Tajemnic', 'Przejdź przez wrota i wkrocz w tajemniczy świat i rozwiąż łamiące umysł zagadki. Wciel się w role poszukiwaczy przygód szukających skarbu z mitycznej legendy!', 79, '2025-05-25', NULL, 1, 1),
(2, 'Podwodny Statek', 'Pogłoski okazały się prawdą i znalazłeś się z bandą w niedawno zatopionej łodzi straszliwego pirata, który siał postrach po 7 morzach i jesteście zdeterminowani do złamania szyfru i dotarcia do jego wielkiego skarbu! ', 89, '2025-05-13', NULL, 1, 2),
(3, 'Baza Księżycowa', 'W tej wyprawie udasz się w podróż na księżyc przy użyciu najnowocześniejszej rakiety Astrono V. Przy tej futurystycznej misji będziesz używał przyszłościowych narzędzi. chodzą pogłoski, że widziano tam Obce organizmy więc BĄDŹ UWAŻNY!', 129, '2025-04-09', NULL, 1, 6),
(4, 'Klątwa Faraona', 'Podczas eksploracji starożytnych piramid w celu odnalezienia sekretów Faraonów popełniliście jeden błąd... ktoś z was zabrał co nie należało do niech. Zmagajcie się z uciekającym tlenem w krypcie i ucieknijcie zanim stanie się waszym grobowcem.', 99, '2024-07-17', NULL, 1, 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `vouchers`
--

CREATE TABLE `vouchers` (
  `idVoucher` int(11) NOT NULL,
  `voName` varchar(25) NOT NULL,
  `voAmount` int(11) NOT NULL,
  `voIsActive` tinyint(1) NOT NULL,
  `voCreation` date NOT NULL,
  `voDeletion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `vouchers`
--

INSERT INTO `vouchers` (`idVoucher`, `voName`, `voAmount`, `voIsActive`, `voCreation`, `voDeletion`) VALUES
(1, 'maj10', 10, 1, '2025-06-02', NULL),
(2, 'lipiec25', 25, 1, '2025-06-01', NULL),
(3, '33proc', 33, 1, '2025-05-02', NULL),
(4, 'FM50', 50, 1, '2025-06-02', NULL),
(5, 'LU90', 90, 1, '2025-06-02', NULL);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`idAccount`);

--
-- Indeksy dla tabeli `accroles`
--
ALTER TABLE `accroles`
  ADD PRIMARY KEY (`idAR`),
  ADD KEY `accro_acc` (`acc_idAccount`),
  ADD KEY `accro_roles` (`roles_idRole`);

--
-- Indeksy dla tabeli `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`idReservation`),
  ADD KEY `res_rooms` (`rooms_idRoom`),
  ADD KEY `res_vo` (`vouchers_idVoucher`),
  ADD KEY `res_acc` (`accounts_idAccount`);

--
-- Indeksy dla tabeli `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`idRole`);

--
-- Indeksy dla tabeli `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`idRoom`);

--
-- Indeksy dla tabeli `vouchers`
--
ALTER TABLE `vouchers`
  ADD PRIMARY KEY (`idVoucher`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `idAccount` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `accroles`
--
ALTER TABLE `accroles`
  MODIFY `idAR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `idReservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `idRole` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `idRoom` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `vouchers`
--
ALTER TABLE `vouchers`
  MODIFY `idVoucher` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accroles`
--
ALTER TABLE `accroles`
  ADD CONSTRAINT `accro_acc` FOREIGN KEY (`acc_idAccount`) REFERENCES `accounts` (`idAccount`),
  ADD CONSTRAINT `accro_roles` FOREIGN KEY (`roles_idRole`) REFERENCES `roles` (`idRole`);

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `res_acc` FOREIGN KEY (`accounts_idAccount`) REFERENCES `accounts` (`idAccount`),
  ADD CONSTRAINT `res_rooms` FOREIGN KEY (`rooms_idRoom`) REFERENCES `rooms` (`idRoom`),
  ADD CONSTRAINT `res_vo` FOREIGN KEY (`vouchers_idVoucher`) REFERENCES `vouchers` (`idVoucher`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
