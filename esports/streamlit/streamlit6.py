import streamlit as st
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

st.title("Line Chart")
chart_data = pd.DataFrame(
    np.random.randn(20,3),
    columns = ['a','b','c']
)
st.line_chart(chart_data)

st.title("Matplotlib chart")
arr = np.random.normal(1,1,size=100)
fig, ax = plt.subplots()
ax.hist(arr, bins=20)
st.pyplot(fig)
