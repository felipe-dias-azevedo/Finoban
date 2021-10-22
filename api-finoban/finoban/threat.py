import os
import json
import datetime
import csv

files = [file for file in os.listdir() if file != os.path.basename(__file__) and not file.endswith('container.json') and file != 'result.csv']
result = [0,0]
passed_results = 0
finished_results = 0
avg_secs_results = []
all_results = []

for file_name in files:
    with open(file=file_name) as test_result:
        result_json = json.loads(test_result.read())

        avg_secs_results.append(result_json['stop'] - result_json['start'])
        
        all_results.append((result_json['fullName'].replace('com.bandtec.br.finoban.', ''), result_json['name'], result_json['status'], result_json['stage'], (result_json['stop'] - result_json['start'])))

        if result_json['stage'] == 'finished':
            finished_results += 1
        if (result_json['status'] == 'passed'):
            passed_results += 1

with open('result.csv', 'w', newline='') as resultfile:
    writer = csv.writer(resultfile, delimiter=';', quotechar='|', quoting=csv.QUOTE_MINIMAL)
    writer.writerow(('classe', 'nomeTeste', 'status', 'estagio', 'duracao'))
    writer.writerows(all_results)

print('-----------------------------------------------')
print('TESTS EXECUTED:', len(files))
print('-----------------------------------------------')
print('SUCCESS:', passed_results)
print('FAILED:', len(files) - passed_results)
print('AVERAGE:', round(100 * passed_results / len(files), 1), '%')
print('-----------------------------------------------')
print('FINISHED:', finished_results)
print('FAILED:', len(files) - finished_results)
print('AVERAGE:', round(100 * finished_results / len(files), 1), '%')
print('-----------------------------------------------')
print('TOTAL SECONDS ELAPSED:', str(datetime.timedelta(milliseconds=sum(avg_secs_results)).seconds), 's')
print('AVERAGE SECONDS ELAPSED:', round(sum(avg_secs_results) / len(avg_secs_results), 1), 'ms')
print('MINIMUM SECONDS ELAPSED:', round(min(avg_secs_results) / len(avg_secs_results), 1), 'ms')
print('MINIMUM SECONDS ELAPSED:', round(max(avg_secs_results) / len(avg_secs_results), 1), 'ms')
print('-----------------------------------------------')
