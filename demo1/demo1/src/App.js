import './App.css';
import image from './img.png'

function App() {
  const clickHello = (event) => {
    event.preventDefault();
    console.log("Hello");
  }
  return (
    <div className="App">
        <h1>Hello World</h1>
        <p>My first paragraph</p>
        <p>--------------------------</p>
        <h2>DANH SÁCH LỚP HỌC</h2>
        <ol>
            <li>Nguyễn Ngọc Duy</li>
            <li>Tống Quang Anh</li>
            <li>Đinh Thu Loan</li>
            <li>Hà Văn Tiến</li>
            <li>Nguyễn Hải Đăng</li>
            <li>Nguyễn Tiến Quang</li>
            <li>Nguyễn Văn Chiến</li>
        </ol>
        <p>--------------------------</p>
        <ol>
            <li>Cơm trưa
              <ul>
                <li>Cơm chiên hải sản</li>
                <li>Cơm sườn non nấu cam</li>
                <li>Cơm canh chua cá lóc</li>  
              </ul>
            </li>
            <li>Tráng miệng trái cây
              <ul>
                <li>Nho tươi</li>
                <li>Chuối</li>
                <li>Mận</li>
              </ul>
            </li>
        </ol>
        <p>--------------------------</p>
        <a href="#" onClick={clickHello}>Hello World</a>
        <p>--------------------------</p>
        <h3 className="orange">Hello World</h3>
        <h3 className="blue">Hello World</h3>
        <h3 className="violet">Hello World</h3>
        <p>--------------------------</p>
        <section>
            <h2>Học lập trình web có đơn giảng không ?</h2>
            <div><img src={image} alt="image not exists" /></div>
            <h3>Đây là tiêu đề của bài viết</h3>
            <p>
              Loren, ipsum dolor sit annet consectetur adipisicring elit.
              Vitae molestias excerciationem, ipsa molestiae, fuga repellat, eligendi eaque error 
              magni rem natus. Debitis quasi quas illum fugit voloptatibus animi quisquam nesciunt!
            </p>
            
            <button type="button" className="btn">Đọc thêm</button>
            
        </section>
    </div>
  );
}
export default App;
