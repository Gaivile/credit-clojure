# Credit

[![Built with Spacemacs](https://cdn.rawgit.com/syl20bnr/spacemacs/442d025779da2f62fc86c2082703697714db6514/assets/spacemacs-badge.svg)](http://spacemacs.org)


A small programming exercise done in Clojure Dojo at Thoughtworks.

The idea is to check user's input for a valid credit/debit card number and if the the card is valid, determine what type of card was it (American Express, MasterCard, Visa, etc). Validation check is done with [Luhn algorithm](https://en.wikipedia.org/wiki/Luhn_algorithm) which is widely used.

If the card is correct, it also checks which type of card it is, and accepts most of the popular cards from [here](https://www.freeformatter.com/credit-card-number-generator-validator.html).

## Usage

Run in REPL.

## License

Copyright © 2017

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
