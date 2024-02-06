package com.github.bkwak.libraryrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}


	/*TODO:
		* wstrzykiwanie komponentów     5
		* pokrycie interfejsami - mvc   5
		* hibernate                     5
		* fronty - fragmenty            5
		* maven                         5
		* sesje                         5
		* webservices                   5
		* architektura                  5
		* jakość kodu        			10

		Requirements: 26
		* Logowanie do aplikacji
		* Możliwość dodawania książek (tytuł, autor, isbn)
		* Możliwość wyszukiwania książek - użytkownik wpisuje tytuł lub autora lub isbn
		(może wpisać niepełny tytuł, niepełnego autora lub niepełny isbn) -
		aplikacja wyświetla listę znalezionych rekordów, dla każdego rekordu pokazuje
		czy książka jest wypożyczona
		i jeśli tak to informację o osobie wypożyczonej i dacie wypożyczenia
		Możliwość wylistowania wypożyczonych książek wraz z informacją o osobie wypożyczającej
		Możliwość wylistowania wypożyczonych książek które dla których data oddania została
		przekroczona wraz z informacją o osobie wypożyczającej i dacie wypożyczenia
		* Możliwość wylistowania wszystkich książek
		* WebService który inicjalizuje dane - jeden do książek, jeden do userów

	 */
}
