import numpy as np
import matplotlib.pyplot as plt

def moving_average(a, n=3) :
    ret = np.cumsum(a, dtype=float)
    ret[n:] = ret[n:] - ret[:-n]
    return ret[n - 1:] / n

def derivative(a):
    return a[1:] - a[:-1]

def plot(y,title=''):
    x = range(len(y))
    plt.plot(x,y)
    if title != '':
        plt.title(title)

def plot_show(y,title=''):
    plot(y,title)
    plt.show()

def ewma(data, window):

    alpha = 2 /(window + 1.0)
    alpha_rev = 1-alpha

    scale = 1/alpha_rev
    n = data.shape[0]

    r = np.arange(n)
    scale_arr = scale**r
    offset = data[0]*alpha_rev**(r+1)
    pw0 = alpha*alpha_rev**(n-1)

    mult = data*pw0*scale_arr
    cumsums = mult.cumsum()
    out = offset + cumsums*scale_arr[::-1]
    return out

with open('experiment.txt','r') as f:
    content = f.read().strip()
lines = content.split('\n')
prev_conf = ''
counter = 0
for idx,line in enumerate(lines):
    fields = line.strip().split(',')
    configuration = ' '.join( fields[0:3] )
    sequence = fields[3]
    title = f'{configuration} {sequence}'

#     if prev_conf == configuration:
#         continue
    if sequence != '84':
        continue

    prev_conf = configuration
    signal = [ int(x) for x in fields[5].split(' ') ]
    avg = moving_average(signal,20)
    d1 = derivative(avg)
    d1 = moving_average(d1 * (abs(d1)>2) , 8)
    d2 = derivative(d1)

#     plot_show(avg, title  + ' - avg')
    plot_show(d1, title + ' - d1')
#     plot_show(d2, title + ' - d2')
    