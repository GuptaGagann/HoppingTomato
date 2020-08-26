-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 10.123.0.59:3307
-- Generation Time: Aug 26, 2020 at 11:20 PM
-- Server version: 5.7.23
-- PHP Version: 7.0.33-0+deb9u6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `swatantranew_hoppingtomato`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id` int(11) NOT NULL,
  `first_name` text NOT NULL,
  `last_name` text NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `role` varchar(1) NOT NULL,
  `dob` varchar(100) NOT NULL,
  `Gender` text NOT NULL,
  `address` varchar(500) DEFAULT NULL,
  `addRegistered` varchar(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Time`, `id`, `first_name`, `last_name`, `user_email`, `user_password`, `role`, `dob`, `Gender`, `address`, `addRegistered`) VALUES
('2020-08-25 20:32:48', 16, 'Gagan', 'Gupta', 'guptagagann@gmail.com', '12344321', '1', '15/8/2020', 'Male', NULL, NULL),
('2020-08-26 22:55:52', 19, 'Naman', 'Gupta', 'namang344@gmail.com', '12345', '1', '6/8/2020', 'Male', NULL, NULL),
('2020-08-26 23:18:23', 1, 'Naman', 'Gupta', 'ngstudies.tk@gmail.com', '12345', '1', '01/01/2001', 'Male', 'Naman Gupta,8604988103,rqg,hwhw,hwjwj,hwjan,hanan,hanan', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
