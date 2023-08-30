import streamlit as st
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

st.header("안녕하세요")
st.write("별거없는 ISTJ")

checkbox_btn = st.checkbox('메뉴 추천')

if checkbox_btn:
    st.write("내일 뭐먹지")

selected_item = st.radio("Radio Part", ("아침","점심","저녁"))
if selected_item == "아침":
    st.write("아침")
elif selected_item == "점심":
    st.write("점심")
elif selected_item == "저녁":
    st.write("저녁")

option = st.selectbox('메뉴 골라주세요', ('일식','중식','한식'))

values = st.slider('음식 가격은?', 0.0, 100.0, (25.0, 75.0))

label = "입력: "
value = "먹고싶은거 적어요"
st.text_input(label, value)

#####s


st.title("Line Chart")
chart_data = pd.DataFrame(
    np.random.randn(20,3),
    columns = ['아침','점심','저녁']
)
st.line_chart(chart_data)

st.title("Matplotlib chart")
arr = np.random.normal(1,1,size=100)
fig, ax = plt.subplots()
ax.hist(arr, bins=20)
st.pyplot(fig)
