from os import path, listdir, remove, popen
from json import loads
import csv
from time import time
from datetime import datetime

CYAN = "\033[96m"
ENDC = "\033[0m"
BOLD = "\033[1m"


def nprint(*args):
    print("[" + CYAN + "FINOBAN" + ENDC + "]", end=" ")
    for arg in args:
        print(BOLD + str(arg) + ENDC, end=" ")
    print()


def getdatewithformat() -> str:
    return datetime.utcnow().replace(second=0, microsecond=0).isoformat()[:-3]+'Z'


def getfiles(files: list) -> list:
    return [file for file in files if not file.endswith('container.json')]


def main():
    datenow = getdatewithformat()
    allurefolder = path.join("target", "allure-results")
    if path.exists(allurefolder):
        oldreports = getfiles(listdir(allurefolder))
        nprint("removing", len(oldreports), "old report files.")
        nprint()
        for report in getfiles(listdir(allurefolder)):
            remove(path.join(allurefolder, report))

    nprint("starting maven tests.")
    beforetime = time()
    maventests = popen('mvn test')
    print(maventests.read())
    aftertime = time()
    nprint("finished maven tests.")
    nprint()
    nprint("tests finished in", round(aftertime - beforetime, 2), "seconds.")
    nprint("maven created", len(getfiles(listdir(allurefolder))), "new report files.")
    nprint()

    nprint("reading allure tests results.")
    assert (path.exists(allurefolder)), "Folder 'allure-results' doesn't exist."
    assert (len(getfiles(listdir(allurefolder))) > 0), "Folder 'allure-results' is empty."
    nprint()

    testresults = []

    for file in getfiles(listdir(allurefolder)):
        with open(file=path.join(allurefolder, file)) as testresult:
            result = loads(testresult.read())
            testresults.append(
                tuple([
                    result['fullName'].replace('com.bandtec.br.finoban.', ''),
                    datenow,
                    (result['stop'] - result['start']),
                    result['stage'],
                    result['name'],
                    result['status']
                ]))

    nprint("generating CSV report file.")
    with open('testresult.csv', 'w', newline='') as resultfile:
        writer = csv.writer(resultfile, delimiter=',', quotechar='"', quoting=csv.QUOTE_ALL, lineterminator='\n')
        writer.writerow(tuple(["CLASSE", "DATAINSERCAO", "DURACAO", "ESTAGIO", "NOMETESTE", "STATUS"]))
        writer.writerows(testresults)
    nprint("successfully generated CSV report file.")


if __name__ == "__main__":
    main()
