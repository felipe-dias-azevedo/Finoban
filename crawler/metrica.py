import matplotlib
import matplotlib.pyplot as plt
import numpy as np

labels = ['centro', 'consolacao', 'interlagos', 'mooca']
means1 = [15, 16, 12, 13]
means2 = [15.5, 16.5, 12, 14]
means3 = [14.5, 15.5, 11, 11]

x = np.arange(len(labels))
fig, ax = plt.subplots()

rects1 = ax.bar(x - 0.35/3, means1, 0.35, label='Cifra')
rects2 = ax.bar(x + 0.35/3,  means2, 0.35, label='S16')
rects3 = ax.bar(x + 0.35/12, means3, 0.35, label='Banco Presil')

ax.set_ylabel('Tempo (anos)')
ax.set_title('Tempo para adquirir o imóvel nas regiões de SP')
ax.set_xticks(x)
ax.set_xticklabels(labels)
ax.legend()

def insert(rects):
    for rect in rects:
        height = rect.get_height()
        ax.annotate('{}'.format(height),
                    xy=(rect.get_x() + rect.get_width() / 2, height),
                    xytext=(0, 3),  # 3 points vertical offset
                    textcoords="offset points",
                    ha='center', va='bottom')

insert(rects1)
insert(rects2)
insert(rects3)
fig.tight_layout()
plt.show()