Утилита FilterFiles_TestTask.jar находится в основной папке проекта, основная ее функция в сортировки данных находящихся в файлах формата .txt и записи отсортированных данных в выходные файлы .txt. 
Сортировка происходит по 3 типам данным: целые числа, дробные числа и строки. При этом диапазон целых чисел от -9 223 372 036 854 775 808 до 9 223 372 036 854 775 807, дробных от +-(4,9e-324) до +-(1,8e+308), при превышения этих диапазонов значение записывается в строковый выходной файл. 
Выходные файлы создаются с именами: integers, floats, strings, в текущей папке (там где располагается утилита). При этом выходные файлы создаются по мере их необходимости.
Аргумент -o {путь} позволяет добавить путь для выходных файлов, аргумент -p {префикс} - префикс перед именем выходного файла.
В консоль выводится информация о запасанных данных в выходные файлы, с аргументом -f - полная статистика, с аргументом -s - краткая статистика. При неуказании аргумента статистики или указании двух сразу выводится сообщение и краткая статистика.
Для добавления результатов в существующие файлы можно использовать аргумент -а.

Для запуска утилиты необходима версия Java 17.
Пример запуска программы из командной строки: java -jar FilterFiles_TestTask.jar -a -p sample in1.txt in2.txt

В коде использована система сборки Maven версии 4.0.0. С строней библиотекой junit:
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>