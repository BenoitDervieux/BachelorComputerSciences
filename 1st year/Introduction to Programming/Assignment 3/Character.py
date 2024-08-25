# --------------------------------------------------------------------------
# Two Star Wars Characters
# File:       Character.py
# Description:
#   This program holds a data class representing a character from Star Wars
#
# History:     9 Oct 2022
#
# ---------------------------------------------------------------------------

class Character:
    def __init__(self, name='', kind='', planet=''):
        self.name = name
        self.kind = kind
        self.planet = planet
        # Initialize the class character

    def to_string(self):
        return f"{self.name} is a(n) {self.kind} from {self.planet}"
        # Returns a string of the character

    def set_name(self, name):
        self.name = name
        # Method that sets the name of the character

    def set_kind(self, kind):
        self.kind = kind
        # Method that sets the kind of the character

    def set_planet(self, planet):
        self.planet = planet
        # Method that sets the planet of the character

    def __repr__(self):
        return '{0} {1} {2}'.format(self.name, self.kind, self.planet)
    # Method explained by Corey Schafer that return an unambiguous value
    # from the object. Link to the set of tutorials
    # https://www.youtube.com/watch?v=ZDa-Z5JzLYM&list=PL-osiE80TeTsqhIuOqKhwlXsIBIdSeYtc

    def __str__(self):
        return '{0:24} {1:12} {2:12}'.format(self.name, self.kind, self.planet)
    # Method explained by Corey Schafer that return readable string
    # from the object. Link to the set of tutorials
    # https://www.youtube.com/watch?v=ZDa-Z5JzLYM&list=PL-osiE80TeTsqhIuOqKhwlXsIBIdSeYtc
