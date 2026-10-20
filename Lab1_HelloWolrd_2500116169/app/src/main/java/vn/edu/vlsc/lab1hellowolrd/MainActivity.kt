package vn.edu.vlsc.lab1hellowolrd

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // 1. Khai báo TAG dùng chung cho toàn bộ Log của màn hình này
    private val TAG = "Lab1_Debug"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2. Phân biệt val (Hằng) và var (Biến)
        val studentId: String = "25001116169" // val: Chỉ đọc (Read-only), không thể gán lại
        var studentName = "Phi Van Cong"        // var: Có thể thay đổi giá trị sau này

        // Trình biên dịch Kotlin tự nhận diện (Type Inference) studentName là String.
        studentName = "Phi Van Cong"

        // 3. Null Safety - Điểm mạnh nhất của Kotlin
        // var nullString: String = null -> Dòng này sẽ bị IDE báo lỗi đỏ ngay lập tức (không cho phép null)

        var nullableName: String? = null // Phải thêm '?' vào sau kiểu dữ liệu để cho phép chứa null
        // Sử dụng toán tử an toàn (?.) và toán tử Elvis (?:)
        // Nếu nullableName khác null thì lấy độ dài, nếu bằng null thì trả về số 0
        val length = nullableName?.length ?: 0

        // 4. In Log để Debug thay vì dùng print()
        Log.i(TAG, "Ứng dụng đã chạy qua hàm onCreate!")
        Log.d(TAG, "Thông tin SV: MSSV: $studentId - Tên: $studentName")
        Log.w(TAG, "Cảnh báo: Biến nullableName đang có độ dài là $length")

        // 5. Thử nghiệm bắt lỗi (Try-Catch) để tránh Crash app
        try {
            val result = 10 / 0
        } catch (e: Exception) {
            Log.e(TAG, "Phát hiện lỗi nghiêm trọng: ${e.message}", e)
        }
    }
}