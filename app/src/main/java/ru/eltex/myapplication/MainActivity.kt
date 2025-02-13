package ru.eltex.myapplication

import android.os.Bundle

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.eltex.myapplication.ui.theme.MyApplicationTheme


//6. Создать программу для вычисления суммы первых n членов арифметической прогрессии (вводятся a,
//d, n, выводятся все члены последовательности и их сумма).

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyUI() //вызываем функцию для построения интерфейса
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyUI() { //функция для построения интерфейса
    var value1 by remember { //объект для работы с текстом, для TextField
        mutableStateOf("") //его начальное значение
    }//в функцию mutableStateOf() в качестве параметра передается отслеживаемое значение
    var value2 by remember { //объект для работы с текстом, для кнопки и суммы
        mutableStateOf("") //его начальное значение
    }
    var result = 0 //будущий результат
    val context = LocalContext.current //объект-контекст, нужен для всплывающего сообщения
    Column( //создаем колонку для вертикального размещения объектов
        modifier = Modifier.fillMaxSize(), //заполняем всё доступное пространство
        horizontalAlignment = Alignment.CenterHorizontally, //по центру горизонтально
        verticalArrangement = Arrangement.Center //и вертикально
    ) {
        TextField( //текстовое поле для ввода данных
            value = value1, //связываем текст из поля с созданным ранее объектом
            onValueChange = { newText -> //обработчик ввода значений в поле
                value1 = newText //все изменения сохраняем в наш объект
            },
            textStyle = TextStyle( //объект для изменения стиля текста
                fontSize = 24.sp //увеличиваем шрифт
            ),
//и меняем тип допустимых символов для ввода – только цифры
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button( //кнопка
            modifier = Modifier.padding(30.dp), //делаем отступ сверху и снизу от кнопки
            onClick = { //обработчик нажатия на кнопку
//разделяем введенные значения через пробел и сохраняем их в виде списка
                val numbers = value1.split(" ").toList()
                // Список разделяю и присваиваю переменным:
                var a0 = numbers[0].toInt()
                val d = numbers[1].toInt()
                var n = numbers[2].toInt()

                // Cоздаю новый список состоящий из членнов прогрессии:
                val membersProgression = mutableListOf((0)) // Список с первым членом прогресси:

                var n_elem = 0;
                var i = 0;
                // Переменныя для вывода членов прогрессии:
                var resultStr = "members: "
                while (n_elem != n ){
                    var an = a0 + (d *(n - 1))
                    resultStr += " $an"
                    membersProgression.add(i+1, an)
                    ++i
                    ++n_elem
                }


//показываем всплывающее сообщение для проверки списка с числами
                Toast.makeText(context, "list = $numbers", Toast.LENGTH_LONG).show()
//в переменную result сохраняем сумму членов прогрессии списка:
                result = (membersProgression.sumOf { it }) / 2
//                var resultStr = "members: "
//                for (member in 0 until n) {
//                    resultStr += " " + membersProgression[member]
//                }
                resultStr += " summ members: $result"
                value2 = resultStr

            }
        ) {
            Text("Ok", fontSize = 24.sp) //текстовая надпись для кнопки
        }
        Text(
            // Проходимся по списку и выводим члены прогрессии:

            // Затем выводим их сумму:
            text = "$value2",
            color = Color.Green, //меняем цвет
            fontSize = 24.sp
        )
    }
}