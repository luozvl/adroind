package vn.edu.vlsc.lab4b_2500116169

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.edu.vlsc.lab4b_2500116169.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {private lateinit var binding: ActivityMainBinding
    private var isCounting = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Button 1: Gây lỗi ANR (Chặn Main Thread)
        binding.btnBlockThread.setOnClickListener {
            binding.tvStatus.text = "Trạng thái: Đang xử lý nặng (Gây đơ UI)..."
            // Sleep chặn đứng luồng chính 5 giây (ProgressBar sẽ đứng im)
            Thread.sleep(5000)
            binding.tvStatus.text = "Trạng thái: Xử lý xong (Main Thread)!"
        }
        // Button 2: Giải pháp với Kotlin Coroutines
        binding.btnRunCoroutine.setOnClickListener {
            binding.tvStatus.text = "Trạng thái: Đang xử lý ngầm bằng Coroutines..."

            // Khởi chạy Coroutine theo vòng đời Activity
            lifecycleScope.launch(Dispatchers.IO) {
                // Gọi hàm xử lý nặng (suspend) trên luồng IO
                val result = doHeavyCalculation()

                // Chuyển luồng cập nhật giao diện an toàn (Main)
                withContext(Dispatchers.Main) {
                    binding.tvStatus.text = result
                }
            }
        }
        startCountdownTimer()
    }
    // Hàm suspend chạy ngầm không chặn luồng
    private suspend fun doHeavyCalculation(): String {
        delay(5000) // Tạm dừng an toàn 5 giây, trả CPU cho UI
        return "Trạng thái: Xử lý xong mượt mà (Coroutines)!"
    }
    // Đồng hồ đếm ngược bất đồng bộ
    private fun startCountdownTimer() {
        if (isCounting) return
        isCounting = true

        lifecycleScope.launch(Dispatchers.Main) {
            for (i in 60 downTo 1) {
                binding.tvCountdown.text = "Thời gian: $i s"
                delay(1000) // Đợi 1 giây, ProgressBar vẫn xoay mượt mà
            }
            binding.tvCountdown.text = "Thời gian: Hết giờ!"
            isCounting = false
        }
    }
}