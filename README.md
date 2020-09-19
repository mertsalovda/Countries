# Countries

<h1>Приложение Страны</br></h1>
<h2>Структура и логика работы</h2>
<p>Состоит из 2 экранов</p>
<p>1 экран список всех стран, которые грузятся с сайта с открытым API 
<span><a target="_blank" rel="noopener noreferrer" href="https://restcountries.eu/">https://restcountries.eu/</a></span>.
При отсутствии сохраненных локально стран, загрузка начинается сразу при входе в приложение. Если
в локальной бд уже есть сохраненные страны – новые можно загрузить через swipe to refresh. Для каждой страны в списке отображается ее название и флаг.
Если не удалось загрузить страны показывается уведомление об ошибке пользователю. По нажатию на элемент списка происходит переход на 2 экран</p>
<p>2 экран – показывает детальную информацию о выбранной на 1 экране стране. Название страны, флаг, валюта, язык, таймзона. По нажатию назад возврат 
на экран со списком стран.</p>
<h2>Демонстрация</h2>
<p>
  <a target="_blank" rel="noopener noreferrer" href="https://github.com/mertsalovda/Countries/blob/master/preview.gif">
    <img src="https://github.com/mertsalovda/Countries/blob/master/preview.gif" alt="" style="max-width:50%;">
  </a>
</p>

<h2>В приложении используются:</h2>
<p> 
Retrofit 2</br>
OkHttp 3</br>
RxJava 2</br>
Kotlin Coroutines</br>
Gson</br>
Glide</br>
Room</br>
androidsvg</br>
Dagger 2</br>
</p>
