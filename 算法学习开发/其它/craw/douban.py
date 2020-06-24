# coding=utf-8

import jieba
import requests
import pandas as pd
import time
import random
from lxml import etree

def main():
    start_spider();

def start_spider():
   base_url = 'https://movie.douban.com/subject/24773958/comments'
   start_url = base_url + '?start=0' 

   number = 1
   html = request_get(start_url) 
   pagecount = 0

   while html.status_code == 200 and pagecount < 20:
       selector = etree.HTML(html.text)   
       comments = selector.xpath("//div[@class='comment']")

       marvelthree = []
       for each in comments:
           marvelthree.append(get_comments(each))

       data = pd.DataFrame(marvelthree)
       try:
           if number == 1:
               csv_headers = ['用户', '是否看过', '五星评分', '评论时间', '有用数', '评论内容']
               data.to_csv('./Marvel3_yingpping.csv', header=csv_headers, index=False, mode='a+', encoding='utf-8')
           else:
               data.to_csv('./Marvel3_yingpping.csv', header=False, index=False, mode='a+', encoding='utf-8')
       except UnicodeEncodeError:
           print("编码错误, 该数据无法写到文件中, 直接忽略该数据")       
       
       data = []
       marvelthree = []

       nextpage = selector.xpath("//div[@id='paginator']/a[@class='next']/@href")
       nextpage = nextpage[0]
       next_url = base_url + nextpage
       html = request_get(next_url)
       pagecount += 1


def request_get(url):
   '''
   使用 Session 能够跨请求保持某些参数。
   它也会在同一个 Session 实例发出的所有请求之间保持 cookie
   '''
   timeout = 3

   UserAgent_List = [
       "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36",
       "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.1 Safari/537.36",
       "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36",
       "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36",
       "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2226.0 Safari/537.36",
       "Mozilla/5.0 (Windows NT 6.4; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2225.0 Safari/537.36",
       "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2225.0 Safari/537.36",
       "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2224.3 Safari/537.36",
       "Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.93 Safari/537.36",
       "Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.93 Safari/537.36",
       "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36",
       "Mozilla/5.0 (Windows NT 4.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36",
       "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.67 Safari/537.36",
       "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.67 Safari/537.36",
       "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.3319.102 Safari/537.36",
       "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.2309.372 Safari/537.36",
       "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.2117.157 Safari/537.36",
       "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.47 Safari/537.36",
       "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1866.237 Safari/537.36",
   ]

   header = {
       'User-agent': random.choice(UserAgent_List),
       'Host': 'movie.douban.com',
       'Referer': 'https://movie.douban.com/subject/24773958/?from=showing',
   }

   session = requests.Session()

   cookie = {
       'cookie': '__utmc=223695111; __utmz=223695111.1527507169.1.1.utmcsr=douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/accounts/login; dbcl2="59482254:yX5jBQIoyj0"; bid=KFXXgGJ2X6w; ck=OJZv; __utmc=30149280; __utmz=30149280.1527507191.1.1.utmcsr=douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/accounts/login; push_noty_num=0; push_doumail_num=0; __utma=30149280.907704983.1527507191.1527507191.1527606142.2; __utmb=30149280.0.10.1527606142; __utma=223695111.656758314.1527507169.1527507169.1527606142.2; __utmb=223695111.0.10.1527606142; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1527606142%2C%22https%3A%2F%2Fwww.douban.com%2Faccounts%2Flogin%3Fsource%3Dmovie%22%5D; _pk_id.100001.4cf6=b90341e1f5b47094.1527507169.2.1527606142.1527507191.; _pk_ses.100001.4cf6=*; ap=1'
   }

   time.sleep(random.randint(5, 15))  
   response = requests.get(url, headers=header, cookies=cookie, timeout = 3)
   if response.status_code != 200:
       print(response.status_code)
   return response


def get_comments(eachComment):
   commentlist = []
   user    = eachComment.xpath("./h3/span[@class='comment-info']/a/text()")[0]  # 用户
   watched = eachComment.xpath("./h3/span[@class='comment-info']/span[1]/text()")[0]  # 是否看过
   rating  = eachComment.xpath("./h3/span[@class='comment-info']/span[2]/@title")  # 五星评分
   comment_time = eachComment.xpath("./h3/span[@class='comment-info']/span[3]/@title")  # 评论时间
   votes   = eachComment.xpath("./h3/span[@class='comment-vote']/span/text()")[0]  # "有用"数
   content = eachComment.xpath("./p/text()")[0]  # 评论内容

   if len(rating) > 0:
       rating = rating[0]
   else:
   		rating = ''

   if len(comment_time) > 0:
       comment_time = comment_time[0]
   else:
       # 有些评论是没有五星评分, 需赋空值
       comment_time = rating
       

   

   commentlist.append(user)
   commentlist.append(watched)
   commentlist.append(rating)
   commentlist.append(comment_time)
   commentlist.append(votes)
   commentlist.append(content.strip())
   # print(commentlist)
   return commentlist




if __name__ == "__main__":
    main()