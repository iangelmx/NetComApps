import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
import matplotlib
matplotlib.style.use('ggplot')

tabla1 = pd.read_csv('Frio.csv')
tabla1 = pd.read_csv('Caliente.csv')

datos1 = tabla1[['Automovil','millasg', 'desp', 'tiempo']]

promedios1 = datos1.apply(np.mean)