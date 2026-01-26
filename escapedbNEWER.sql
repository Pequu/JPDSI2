-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sty 26, 2026 at 12:58 PM
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
(1, 'administrator', 'adminowski', '2026-01-01', 1, '2025-05-23', NULL, '$2a$10$ivYZ45eV/Ks.nOEX3uTevuQah/qr0MS.PmCLwqP7.SkOwHoC/S95m', 'admin'),
(3, 'Mateusz', 'Rzymowski', '2002-09-27', 1, '2025-05-23', NULL, '$2y$10$qE8ueykvbsjBOVjDagvVYO96/7X7uVNwRvU/c6sBnQCyGT/iOS9DC', 'mati'),
(10, 'Emilia', 'Dąbrowska', '2003-03-10', 1, '2025-05-29', NULL, '$2y$10$/.mdPF57oK71c98g/11QzeuTk1GmvNn/3EQee54dsrfwoe4IfuVSe', 'emson07'),
(12, 'test', 'test', '1993-07-01', 1, '2025-05-29', NULL, '$2a$10$ivYZ45eV/Ks.nOEX3uTevuQah/qr0MS.PmCLwqP7.SkOwHoC/S95m', 'test'),
(13, '123', '123', '2015-12-02', 1, '2025-12-08', NULL, '$2a$10$ivYZ45eV/Ks.nOEX3uTevuQah/qr0MS.PmCLwqP7.SkOwHoC/S95m', '123'),
(16, 'Adamiec', 'Kruk', '2025-12-09', 1, '2026-01-26', NULL, '$2a$10$ivYZ45eV/Ks.nOEX3uTevuQah/qr0MS.PmCLwqP7.SkOwHoC/S95m', 'adam'),
(17, 'Ewa', 'Tracz', '2019-04-03', 1, '2026-01-26', NULL, '$2a$10$BANx5xuohZN0cDO40E2RReqnMmBFODCkoNplPD0ZJimBAQgWwysMG', 'ewa1');

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
(7, 3, 2),
(9, 10, 3),
(11, 12, 3),
(12, 13, 3),
(14, 16, 2),
(15, 17, 3);

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
(9, '2025-06-03', NULL, 79, 1, 1, NULL, 10),
(10, '2026-01-30', NULL, 89, 1, 2, NULL, 10),
(11, '2025-06-18', NULL, 99, 1, 4, NULL, 10),
(12, '2025-07-10', NULL, 89, 1, 4, 1, 10),
(13, '2025-07-18', NULL, 104, 1, 3, 2, 10),
(14, '2025-09-18', NULL, 96, 1, 3, 3, 10),
(15, '2026-01-29', NULL, 129, 1, 3, NULL, 10),
(16, '2025-06-03', 1, 79, 1, 1, NULL, 10),
(22, '2025-06-02', 2, 9, 1, 4, 5, 10),
(23, '2026-01-28', 1, 129, 1, 3, NULL, 12),
(24, '2026-01-30', NULL, 129, 1, 3, NULL, 12),
(25, '2026-02-20', NULL, 99, 1, 4, NULL, 12),
(26, '2026-01-27', NULL, 99, 1, 4, NULL, 12),
(27, '2026-01-31', 1, 79, 1, 1, NULL, 12),
(28, '2026-01-28', 2, 79, 1, 1, NULL, 12),
(29, '2026-02-12', 2, 89, 1, 2, NULL, 12),
(30, '2026-02-12', NULL, 79, 1, 1, NULL, 12),
(31, '2026-02-13', 1, 79, 1, 1, NULL, 12),
(33, '2026-02-05', 1, 99, 1, 4, NULL, 12),
(34, '2026-01-30', 1, 99, 1, 4, NULL, 12),
(35, '2026-01-29', 2, 10, 0, 4, 5, 12),
(36, '2026-01-30', 2, 50, 1, 4, 4, 12),
(37, '2026-02-02', 1, 89, 0, 2, NULL, 12),
(38, '2026-02-08', 1, 40, 0, 1, 4, 12),
(39, '2026-02-23', 2, 71, 1, 1, 1, 12),
(40, '2026-02-21', 2, 79, 0, 1, NULL, 12),
(41, '2026-02-17', 1, 89, 0, 2, NULL, 12),
(42, '2026-02-13', 1, 89, 0, 2, NULL, 12),
(43, '2026-02-15', 1, 99, 0, 4, NULL, 12),
(44, '2026-02-21', 2, 71, 0, 1, 1, 12),
(45, '2026-02-07', 2, 10, 0, 4, 5, 12);

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
(3, 'user', 1, '2025-05-25', NULL);

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
  MODIFY `idAccount` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `accroles`
--
ALTER TABLE `accroles`
  MODIFY `idAR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `idReservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

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
