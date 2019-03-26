file = open('../data/input1.txt', 'r')
global token

def get_token():
    token = file.readline().strip()
    print token
    return token

def factor():
    print "factor"
    return get_token() in ["id", "num"]

def expression_prime():
    print "expression_prime"
    token = get_token()
    if token in ["+", "-"] and factor():
        token = get_token()
        return token == ";"
    return token == ";"

def expression():
    print "expression"
    if not factor():
        return False
    if not expression_prime():
        return False
    return True

def statement():
    print "statement"
    if get_token() != "id":
        return False
    if get_token() != "=":
        return False
    if not expression():
        return False
    return True

def statement_list_prime():
    print "statement_list_prime"
    if not statement_list():
        return token == "end"
    return True

def statement_list():
    print "statement_list 1"
    if not statement():
        return False
    if not statement_list_prime():
        return False
    return True

def program():
    if get_token() != "begin":
        return False
    if not statement_list():
        return False
    if get_token() != "end":
        return False
    return True

if __name__ == '__main__':
    print program()
