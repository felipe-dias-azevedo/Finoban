import sqlite3

def see_all():
    con = sqlite3.connect("s16bank.db")
    c = con.cursor()
    data = c.execute("SELECT * FROM Cliente")
    data = data.fetchall()
    print(data)

def insert():
    con = sqlite3.connect("s16bank.db")
    c = con.cursor()

    c.execute(
        "CREATE TABLE IF NOT EXISTS Cliente ( \
            CNPJ INTEGER PRIMARY KEY, \
            Nome TEXT, \
            Patrimonio REAL, \
            DataNascimento DATE \
        )"
    )

    data = (
        (123456789000, 'Carlos Roberto', 50000.0, "1985-12-16"),
        (12345, 'João da Silva', 10000.0, "1993-05-24"),
        (123, 'Silvio Santos',  1000000.0, "1970-01-01"),
        (456, 'Gabriel Rodrigues',  50000.0, "1995-03-27"),
        (789, 'Fernando Brandão',  10000.0, "1998-06-14")
    )
    
    for d in data:
        c.execute(
            "INSERT INTO Cliente (CNPJ, Nome, Patrimonio, DataNascimento) VALUES (?, ?, ?, ?)", d
        )
    con.commit()


if __name__ == "__main__":
    insert()
    # see_all()