# coding=utf-8

import jieba
import requests
import pandas as pd
import time
import random
import codecs
import csv
from lxml import etree

def main():
    split_word();

def split_word():
   with codecs.open('Marvel3_yingpping.csv', 'r', 'utf-8') as csvfile:
       reader = csv.reader(utf_8_encoder(csvfile))
       content_list = []
       for row in reader:
           try:
               content_list.append(row[5])
           except IndexError:
               pass

       content = ''.join(content_list)
       # print(content)

       seg_list = jieba.cut(content, cut_all=False)
       result = '\n'.join(seg_list)
       print(result)


def utf_8_encoder(unicode_csv_data):
    for line in unicode_csv_data:
        yield line.encode('utf-8')


if __name__ == "__main__":
    main()