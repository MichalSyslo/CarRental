-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 04 Mar 2021, 18:00
-- Wersja serwera: 10.4.14-MariaDB
-- Wersja PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `carrental`
--
CREATE DATABASE IF NOT EXISTS `carrental` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `carrental`;

DELIMITER $$
--
-- Procedury
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAvailableVehicles` (IN `startDate` TIMESTAMP, IN `endDate` TIMESTAMP)  NO SQL
SELECT * FROM tvehicle WHERE tvehicle.id NOT IN 
(SELECT vehicle_id FROM treservation 
 WHERE (startDate >= treservation.startDate AND startDate <= treservation.endDate)
 OR (startDate <= treservation.startDate AND endDate >= treservation.startDate))$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Zastąpiona struktura widoku `fullreservationinfo`
-- (Zobacz poniżej rzeczywisty widok)
--
CREATE TABLE `fullreservationinfo` (
`login` varchar(255)
,`user_name` varchar(255)
,`surname` varchar(255)
,`reservation_id` int(11)
,`startDate` datetime
,`endDate` datetime
,`totalPrice` double
,`bootCapacity` int(11)
,`gearbox` varchar(255)
,`mileage` double
,`vehicle_name` varchar(255)
,`seats` int(11)
);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `treservation`
--

CREATE TABLE `treservation` (
  `id` int(11) NOT NULL,
  `endDate` datetime DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `totalPrice` double NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `vehicle_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `treservation`
--

INSERT INTO `treservation` (`id`, `endDate`, `startDate`, `totalPrice`, `user_id`, `vehicle_id`) VALUES
(10, '2021-02-05 11:00:00', '2021-01-27 11:00:00', 210, 3, 5),
(13, '2020-12-09 14:00:00', '2020-12-01 14:00:00', 120, 9, 1),
(15, '2021-03-06 16:00:00', '2021-03-04 16:00:00', 100.14, 3, 5),
(16, '2021-04-07 10:00:00', '2021-03-30 10:00:00', 792, 3, 1),
(17, '2021-05-09 06:00:00', '2021-05-01 06:00:00', 560, 9, 14),
(18, '2021-03-21 10:00:00', '2021-03-18 10:00:00', 297, 3, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tuser`
--

CREATE TABLE `tuser` (
  `id` int(11) NOT NULL,
  `login` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `surname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `tuser`
--

INSERT INTO `tuser` (`id`, `login`, `name`, `password`, `role`, `surname`) VALUES
(1, 'admin', 'michal', 'admin', 'ADMIN', 'syslo'),
(3, 'michal', 'michal', 'michal', 'USER', 'michal'),
(9, 'adam123', 'Adam', 'adam123', 'USER', 'Adamowski');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tvehicle`
--

CREATE TABLE `tvehicle` (
  `id` int(11) NOT NULL,
  `bootCapacity` int(11) NOT NULL,
  `gearbox` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mileage` double NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` double NOT NULL,
  `seats` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `tvehicle`
--

INSERT INTO `tvehicle` (`id`, `bootCapacity`, `gearbox`, `mileage`, `name`, `price`, `seats`) VALUES
(1, 230, 'manual', 5, 'Toyota yaris', 99, 5),
(5, 333, 'manual', 6.5, 'Opel Astra', 50.07, 5),
(14, 410, 'automatic', 7.1, 'Toyota Corolla', 70, 5);

-- --------------------------------------------------------

--
-- Struktura widoku `fullreservationinfo`
--
DROP TABLE IF EXISTS `fullreservationinfo`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `fullreservationinfo`  AS SELECT `tuser`.`login` AS `login`, `tuser`.`name` AS `user_name`, `tuser`.`surname` AS `surname`, `treservation`.`id` AS `reservation_id`, `treservation`.`startDate` AS `startDate`, `treservation`.`endDate` AS `endDate`, `treservation`.`totalPrice` AS `totalPrice`, `tvehicle`.`bootCapacity` AS `bootCapacity`, `tvehicle`.`gearbox` AS `gearbox`, `tvehicle`.`mileage` AS `mileage`, `tvehicle`.`name` AS `vehicle_name`, `tvehicle`.`seats` AS `seats` FROM ((`tuser` join `treservation` on(`tuser`.`id` = `treservation`.`user_id`)) join `tvehicle` on(`treservation`.`vehicle_id` = `tvehicle`.`id`)) ;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `treservation`
--
ALTER TABLE `treservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs3k1xnfkriyq6qbewhhiil316` (`user_id`),
  ADD KEY `FK3bnf5w11psyka3el8qectj4h6` (`vehicle_id`);

--
-- Indeksy dla tabeli `tuser`
--
ALTER TABLE `tuser`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `tvehicle`
--
ALTER TABLE `tvehicle`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `treservation`
--
ALTER TABLE `treservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT dla tabeli `tuser`
--
ALTER TABLE `tuser`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT dla tabeli `tvehicle`
--
ALTER TABLE `tvehicle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
