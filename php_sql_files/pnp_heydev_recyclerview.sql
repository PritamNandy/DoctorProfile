-- phpMyAdmin SQL Dump
-- version 4.9.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 27, 2020 at 07:00 AM
-- Server version: 10.3.22-MariaDB-log-cll-lve
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `codearistosio_recyclerview`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctors`
--

CREATE TABLE `doctors` (
  `id` int(100) NOT NULL,
  `name` varchar(500) NOT NULL,
  `hospital` varchar(500) NOT NULL,
  `rating` float NOT NULL,
  `visit` double NOT NULL,
  `image` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctors`
--

INSERT INTO `doctors` (`id`, `name`, `hospital`, `rating`, `visit`, `image`) VALUES
(1, 'Dr. Diptta Bhattacharjee', 'Raojan Medical', 4.7, 350, 'https://image.flaticon.com/icons/png/512/504/504061.png'),
(2, 'Dr. Fahima Binte Younus', 'Hathajhari Union Hospital', 4.8, 250, 'https://image.flaticon.com/icons/png/512/1869/1869354.png'),
(3, 'Dr. Ema Dhar', 'Max Hospital and Diagnostic Center', 4.2, 400, 'https://image.flaticon.com/icons/png/512/1869/1869354.png'),
(4, 'Dr. Farhan Uddin', 'Sensive Hospital', 3.8, 450, 'https://image.flaticon.com/icons/png/512/504/504061.png'),
(5, 'Dr. Tonmoy Saha', 'Chattogram Medical College', 4.5, 200, 'https://image.flaticon.com/icons/png/512/504/504061.png'),
(6, 'Dr. Rezaul Karim', 'Max Hospital & Diagnostic Center', 4.1, 250, 'https://image.flaticon.com/icons/png/512/504/504061.png'),
(7, 'Dr. Sharmistha Roy', 'Shevron Hospital', 4.6, 400, 'https://image.flaticon.com/icons/png/512/1869/1869354.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doctors`
--
ALTER TABLE `doctors`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
