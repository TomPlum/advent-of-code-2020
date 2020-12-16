# :christmas_tree: Advent of Code (2020)

[![GitHub Issues](https://img.shields.io/github/issues/TomPlum/advent-of-code-2020.svg)](https://github.com/TomPlum/advent-of-code-2020/issues)
![GitHub closed issues](https://img.shields.io/github/issues-closed/TomPlum/advent-of-code-2020?color=brightgreen)
![GitHub](https://img.shields.io/github/license/TomPlum/advent-of-code-2020?color=informational)
![GitHub](https://img.shields.io/badge/instructions-99%25-success)
![GitHub](https://img.shields.io/badge/branches-93%25-orange)

## What is Advent of Code?

_Excerpt from the Advent of Code [website](https://adventofcode.com/2020/about);_

    "Advent of Code is an Advent calendar of small programming puzzles for a variety of skill sets
    and skill levels that can be solved in any programming language you like. People use them as a
    speed contest, interview prep, company training, university coursework, practice problems, or
    to challenge each other."
    
## About
After much deliberation I (anti-climatically) decided to take the same approach as 
[last year](https://github.com/TomPlum/advent-of-code-2019) and take my time with each puzzle by taking a more
enterprise-style test-driven approach in Kotlin as I really enjoyed it.
    
## Contents
* [Getting Started](#getting-started)
* [The Codebase](#the-codebase)
  * [Package Structure](#package-structure)
* [Answer Table](#answer-table)

## Getting Started
Simply clone or download the repository into your local environment and import it as a Gradle project in your IDE.
Running [Solutions.kt](https://git.io/JII6v) will run the parts from all the completed days, reporting all the
answers and runtimes in the console.

### Gradle Tasks
| Task               | Description                                                                                       |
|--------------------|---------------------------------------------------------------------------------------------------|
| `test`             | Runs the unit tests with coverage for the `implementation`, `solutions` and `common` sub-projects.|

### IntelliJ Run Configurations
The `.run` directory contains XML configuration files from IntelliJ. Included are configurations for running the unit
tests in the `common`, `implementation` and `solutions` Gradle sub-projects as well as for each specific day.

## The Codebase
### Package Structure
The package structure was something that changed almost every day. My goal was "package-by-feature". For the first few 
days it seemed like I'd just end up having 25 different packages whose names were relevant keywords from that day. 
However, towards the latter half of the days, there were consistencies in the naming that started to make the language 
a little more ubiquitous. This allowed me to start grouping packages together and start abstracting out common code.

I created two Gradle root projects, `implementation` and `solutions`. With the former having sub-projects, `common`, for
behaviour that is commonly used across multiple days and `test-support` for unit test utility classes.

    -implementation
        -src
        -common
        -test-support
    -solutions

## Answer Table

| Day 	| Part 1 	     | Part 2 	         | Name                                      | Documentation          |
|-------|----------------|-------------------|-------------------------------------------|------------------------|
| 01   	| 802011         | 248607374         | Report Repair                             | [Link](docs/DAY1.MD)   |
| 02   	| 660            | 530               | Password Philosophy                       | [Link](docs/DAY2.MD)   |
| 03   	| 169            | 7560370818        | Toboggan Trajectory                       | [Link](docs/DAY3.MD)   |
| 04   	| 192            | 101               | Passport Processing                       | [Link](docs/DAY4.MD)   |
| 05   	| 904            | 669               | Binary Boarding                           | [Link](docs/DAY5.MD)   |
| 06   	| 6911           | 3473              | Custom Customs                            | [Link](docs/DAY6.MD)   |
| 07   	| 300            | 8030              | Handy Haversacks                          | [Link](docs/DAY7.MD)   |
| 08   	| 1832           | 662               | Handheld Halting                          | [Link](docs/DAY8.MD)   |
| 09   	| 20874512       | 3012420           | Encoding Error                            | [Link](docs/DAY9.MD)   |
| 10   	| 2176           | 18512297918464    | Adapter Array                             | [Link](docs/DAY10.MD)  |
| 11   	| -              | -                 |                                           |                        |
| 12   	| 319            | 50157             | Rain Risk                                 | [Link](docs/DAY12.MD)  |
| 13   	| 102            | 327300950120029   | Shuttle Search                            | [Link](docs/DAY13.MD)  |
| 14   	| 9967721333886  | 4355897790573     | Docking Data                              | [Link](docs/DAY14.MD)  |
| 15   	| 1259           | 689               | Rambunctious Recitation                   | [Link](docs/DAY15.MD)  |
| 16   	| 28882          | 1429779530273     | Ticket Translation                        | [Link](docs/DAY16.MD)  |
| 17   	| -              | -                 |                                           |                        |
| 18   	| -              | -                 |                                           |                        |
| 19   	| -              | -                 |                                           |                        |
| 20   	| -              | -                 |                                           |                        |
| 21   	| -              | -                 |                                           |                        |
| 22   	| -              | -                 |                                           |                        |
| 23   	| -              | -                 |                                           |                        |
| 24   	| -              | -                 |                                           |                        |
| 25   	| -              | -                 |                                           |                        |