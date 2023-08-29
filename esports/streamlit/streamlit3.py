import streamlit as st
option = st.selectbox('please select in selectbox!', ('faker','doran','chovy'))
st.write("you select:", option)

multi_select = st.multiselect('Please select in selectibox',{'top','jg','mid','adc','spt'})
st.write("you can play", multi_select)

values = st.slider('select a range of values', 0.0, 100.0, (25.0, 75.0))
st.write('values', values)