/**
 * Реализовать сортировку подсчетом.
 *
 *
static void countingSort(int[] array) {

        }
*/
import java.util.Arrays;

class CountingSort {
    public static void main(String[] args) {
        int[] array = {4, 2, 2, 8, 3, 3, 1, 5, 6, 5};
        countingSort(array);
        System.out.println(Arrays.toString(array));
    }

    static void countingSort(int[] array) {
        if (array.length == 0) {
            return;
        }

        // Находим минимальное и максимальное значения в массиве
        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            } else if (array[i] > max) {
                max = array[i];
            }
        }

        // Определяем диапазон значений
        int range = max - min + 1;
        int[] count = new int[range]; // Создаем массив-счетчик

        // Подсчитываем количество повторений каждого элемента
        for (int i = 0; i < array.length; i++) {
            count[array[i] - min]++;
        }

        // Находим кумулятивные суммы в массиве-счетчике
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        int[] sortedArray = new int[array.length]; // Создаем новый массив для отсортированных элементов
        // Переносим элементы из исходного массива в отсортированный массив в правильном порядке
        for (int i = array.length - 1; i >= 0; i--) {
            int value = array[i];
            int position = count[value - min]--; // Получаем позицию элемента в отсортированном массиве
            sortedArray[position - 1] = value;
        }

        System.arraycopy(sortedArray, 0, array, 0, array.length); // Копируем отсортированный массив
        // обратно в исходный массив
    }
}

