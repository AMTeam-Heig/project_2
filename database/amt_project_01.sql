-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : db
-- Généré le : jeu. 29 oct. 2020 à 21:30
-- Version du serveur :  8.0.21
-- Version de PHP : 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `amt_project_01`
--

-- --------------------------------------------------------

--
-- Structure de la table `Answer`
--

CREATE TABLE `Answer` (
  `idAnswer` varchar(255) NOT NULL,
  `text` varchar(400) NOT NULL,
  `idQuestion` varchar(255) NOT NULL,
  `idUser` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `Answer`
--

INSERT INTO `Answer` (`idAnswer`, `text`, `idQuestion`, `idUser`) VALUES
('40573c93-8ccf-4ac1-bfd6-5b7e1272b5bf', 'Bears eat beats, bears beat battlestar galactica ! ', '393e48f8-4a7e-408d-94ea-b161f1015a5a', '14a31be5-d8de-4c30-9344-82aff5b0b5be'),
('61ea9ff9-d527-4369-9d65-690a8138198f', 'Both are good :)', '34135fb3-4875-42ba-80ea-7f27c8ec58d5', '14a31be5-d8de-4c30-9344-82aff5b0b5be'),
('75383164-f2e4-4dcc-9b9b-fe69c71b1ed8', 'There are basically two strings of thoughts...', '393e48f8-4a7e-408d-94ea-b161f1015a5a', '3c931011-d66c-4d06-a7ee-c20ba0cd84cf'),
('c18edf95-362b-4dbd-9ba4-b628180cd099', 'All the chis :):)', '34135fb3-4875-42ba-80ea-7f27c8ec58d5', '1ba0d8b1-629e-4dfc-a199-92ac5c992a54'),
('d80170f2-998c-44f3-9eee-7725bcb6a22c', 'Walidou all the way!!', '2403bd8a-1fd8-4e2b-b4d2-33f2fe35b509', '1ba0d8b1-629e-4dfc-a199-92ac5c992a54'),
('fed9a39b-00dd-4954-99c2-0f365db5ad1d', 'You don\'t kill it, you adopt it and make it kill for you ', '96ecdc60-f619-475d-bca7-81c660b83804', '14a31be5-d8de-4c30-9344-82aff5b0b5be');

-- --------------------------------------------------------

--
-- Structure de la table `Question`
--

CREATE TABLE `Question` (
  `idQuestion` varchar(255) NOT NULL,
  `text` varchar(500) NOT NULL,
  `author` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `Question`
--

INSERT INTO `Question` (`idQuestion`, `text`, `author`) VALUES
('2403bd8a-1fd8-4e2b-b4d2-33f2fe35b509', 'La Walance ou Walidou ? Vous avez trois heures...', '14a31be5-d8de-4c30-9344-82aff5b0b5be'),
('34135fb3-4875-42ba-80ea-7f27c8ec58d5', 'Which is tastier, sushi or mochi ?', '3c931011-d66c-4d06-a7ee-c20ba0cd84cf'),
('393e48f8-4a7e-408d-94ea-b161f1015a5a', 'Which is stronger, bears or beats?', '1ba0d8b1-629e-4dfc-a199-92ac5c992a54'),
('96ecdc60-f619-475d-bca7-81c660b83804', 'How do you kill a dragon?', '3c931011-d66c-4d06-a7ee-c20ba0cd84cf'),
('f0ce7d92-8a56-4510-a95d-a399a87e7dd5', 'Is there any rest for the wicked?', '14a31be5-d8de-4c30-9344-82aff5b0b5be'),
('f706743a-b9a4-482b-909e-bbdc774f4093', 'How old to you think I am?', '3c931011-d66c-4d06-a7ee-c20ba0cd84cf');

-- --------------------------------------------------------

--
-- Structure de la table `User`
--

CREATE TABLE `User` (
  `idUser` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `User`
--

INSERT INTO `User` (`idUser`, `username`, `lastname`, `firstname`, `email`, `password`) VALUES
('14a31be5-d8de-4c30-9344-82aff5b0b5be', 'Walidou', 'Massaoudi', 'Walid', 'massaoulid@wawa.com', '$2a$10$CYd/XqbxhDj4pPmx/ppYjeHMdUyNP7sTVAzwLo9Txc/X0pn.53GV6'),
('1ba0d8b1-629e-4dfc-a199-92ac5c992a54', 'Clacla', 'Fleurimont', 'Clarisse', 'clarisse.fleu@gmail.com', '$2a$10$Y1Xhi4WdfSt/Zjcnu73o8.tISNSItdinu2smCLQCvmPkbsJJq.24y'),
('3c931011-d66c-4d06-a7ee-c20ba0cd84cf', 'CosmicElodie', 'Lagier', 'Elodie', 'elogier@mail.moi', '$2a$10$Wr7m6hyWJFqpD5sq9ISg/eSzaEcuCvxBwPmybyEuvX29XFWza9sny');

-- --------------------------------------------------------

--
-- Structure de la table `User_comments_Answer`
--

CREATE TABLE `User_comments_Answer` (
  `idUser` varchar(255) NOT NULL,
  `idAnswer` varchar(255) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `idComment` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `User_comments_Answer`
--

INSERT INTO `User_comments_Answer` (`idUser`, `idAnswer`, `comment`, `idComment`) VALUES
('1ba0d8b1-629e-4dfc-a199-92ac5c992a54', 'fed9a39b-00dd-4954-99c2-0f365db5ad1d', 'Good plan !', 'c1238d9d-d794-464d-916d-504d842a9208');

-- --------------------------------------------------------

--
-- Structure de la table `User_comments_Question`
--

CREATE TABLE `User_comments_Question` (
  `idUser` varchar(255) NOT NULL,
  `idQuestion` varchar(255) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `idComment` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `User_comments_Question`
--

INSERT INTO `User_comments_Question` (`idUser`, `idQuestion`, `comment`, `idComment`) VALUES
('14a31be5-d8de-4c30-9344-82aff5b0b5be', '34135fb3-4875-42ba-80ea-7f27c8ec58d5', 'Are you that hungry?', '8af26cc4-c6cc-4d67-9273-18bcb75801f5'),
('14a31be5-d8de-4c30-9344-82aff5b0b5be', '96ecdc60-f619-475d-bca7-81c660b83804', 'Are you crazy or just dumb?', '94f2e640-8d2d-4b0d-8fda-dd41386a64ce');

-- --------------------------------------------------------

--
-- Structure de la table `User_votes_for_Answer`
--

CREATE TABLE `User_votes_for_Answer` (
  `idUser` varchar(255) NOT NULL,
  `idAnswer` varchar(255) NOT NULL,
  `isUpvote` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `User_votes_for_Answer`
--

INSERT INTO `User_votes_for_Answer` (`idUser`, `idAnswer`, `isUpvote`) VALUES
('1ba0d8b1-629e-4dfc-a199-92ac5c992a54', '61ea9ff9-d527-4369-9d65-690a8138198f', 'true'),
('1ba0d8b1-629e-4dfc-a199-92ac5c992a54', 'fed9a39b-00dd-4954-99c2-0f365db5ad1d', 'true');

-- --------------------------------------------------------

--
-- Structure de la table `User_votes_for_Question`
--

CREATE TABLE `User_votes_for_Question` (
  `idUser` varchar(255) NOT NULL,
  `idQuestion` varchar(255) NOT NULL,
  `isUpvote` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `User_votes_for_Question`
--

INSERT INTO `User_votes_for_Question` (`idUser`, `idQuestion`, `isUpvote`) VALUES
('14a31be5-d8de-4c30-9344-82aff5b0b5be', '34135fb3-4875-42ba-80ea-7f27c8ec58d5', 'true'),
('14a31be5-d8de-4c30-9344-82aff5b0b5be', '393e48f8-4a7e-408d-94ea-b161f1015a5a', 'true'),
('14a31be5-d8de-4c30-9344-82aff5b0b5be', '96ecdc60-f619-475d-bca7-81c660b83804', 'false'),
('1ba0d8b1-629e-4dfc-a199-92ac5c992a54', '2403bd8a-1fd8-4e2b-b4d2-33f2fe35b509', 'true');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Answer`
--
ALTER TABLE `Answer`
  ADD PRIMARY KEY (`idAnswer`),
  ADD UNIQUE KEY `idAnswer_UNIQUE` (`idAnswer`),
  ADD KEY `fk_Answer_Question1_idx` (`idQuestion`),
  ADD KEY `fk_Answer_User1_idx` (`idUser`);

--
-- Index pour la table `Question`
--
ALTER TABLE `Question`
  ADD PRIMARY KEY (`idQuestion`),
  ADD UNIQUE KEY `idQuestion_UNIQUE` (`idQuestion`),
  ADD KEY `fk_Question_User1_idx` (`author`);

--
-- Index pour la table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`idUser`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `idUser_UNIQUE` (`idUser`);

--
-- Index pour la table `User_comments_Answer`
--
ALTER TABLE `User_comments_Answer`
  ADD PRIMARY KEY (`idUser`,`idAnswer`,`idComment`),
  ADD UNIQUE KEY `idComment_UNIQUE` (`idComment`),
  ADD KEY `fk_User_has_Answer_Answer1_idx` (`idAnswer`),
  ADD KEY `fk_User_has_Answer_User1_idx` (`idUser`);

--
-- Index pour la table `User_comments_Question`
--
ALTER TABLE `User_comments_Question`
  ADD PRIMARY KEY (`idUser`,`idQuestion`,`idComment`),
  ADD KEY `fk_User_has_Question_Question1_idx` (`idQuestion`),
  ADD KEY `fk_User_has_Question_User_idx` (`idUser`);

--
-- Index pour la table `User_votes_for_Answer`
--
ALTER TABLE `User_votes_for_Answer`
  ADD PRIMARY KEY (`idUser`,`idAnswer`),
  ADD KEY `fk_User_has_Answer_Answer1_idx` (`idAnswer`),
  ADD KEY `fk_User_has_Answer_User1_idx` (`idUser`);

--
-- Index pour la table `User_votes_for_Question`
--
ALTER TABLE `User_votes_for_Question`
  ADD PRIMARY KEY (`idUser`,`idQuestion`),
  ADD KEY `fk_User_has_Question_Question1_idx` (`idQuestion`),
  ADD KEY `fk_User_has_Question_User_idx` (`idUser`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Answer`
--
ALTER TABLE `Answer`
  ADD CONSTRAINT `fk_Answer_Question1` FOREIGN KEY (`idQuestion`) REFERENCES `Question` (`idQuestion`),
  ADD CONSTRAINT `fk_Answer_User1` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`);

--
-- Contraintes pour la table `Question`
--
ALTER TABLE `Question`
  ADD CONSTRAINT `fk_Question_User1` FOREIGN KEY (`author`) REFERENCES `User` (`idUser`);

--
-- Contraintes pour la table `User_comments_Answer`
--
ALTER TABLE `User_comments_Answer`
  ADD CONSTRAINT `fk_User_has_Answer_Answer10` FOREIGN KEY (`idAnswer`) REFERENCES `Answer` (`idAnswer`),
  ADD CONSTRAINT `fk_User_has_Answer_User10` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`);

--
-- Contraintes pour la table `User_comments_Question`
--
ALTER TABLE `User_comments_Question`
  ADD CONSTRAINT `fk_User_has_Question_Question10` FOREIGN KEY (`idQuestion`) REFERENCES `Question` (`idQuestion`),
  ADD CONSTRAINT `fk_User_has_Question_User0` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`);

--
-- Contraintes pour la table `User_votes_for_Answer`
--
ALTER TABLE `User_votes_for_Answer`
  ADD CONSTRAINT `fk_User_has_Answer_Answer1` FOREIGN KEY (`idAnswer`) REFERENCES `Answer` (`idAnswer`),
  ADD CONSTRAINT `fk_User_has_Answer_User1` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`);

--
-- Contraintes pour la table `User_votes_for_Question`
--
ALTER TABLE `User_votes_for_Question`
  ADD CONSTRAINT `fk_User_has_Question_Question1` FOREIGN KEY (`idQuestion`) REFERENCES `Question` (`idQuestion`),
  ADD CONSTRAINT `fk_User_has_Question_User` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
