import streamlit as st
label = "입력: "
value = "사용자 입력 예시"
st.text_input(label, value)

st.text_input(label, value, type = "password")

st.text_area(label, value)

st.date_input(label)

st.time_input(label)