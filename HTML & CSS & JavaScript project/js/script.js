// to open and close shopping cart
let cartItem = document.querySelector('.cart-items-container');
document.querySelector('#cart-btn').onclick = () =>
{
    cartItem.classList.toggle('active');
   
}



// using collection to make text red or white

    function myFunction() {
      const myCollection = document.getElementsByTagName("h2");
      for (let i = 0; i < myCollection.length; i++) {
        myCollection[i].style.color = "red";
      }
    }
  
    function myFunction2() {
      const myCollection = document.getElementsByTagName("h2");
      for (let i = 0; i < myCollection.length; i++) {
        myCollection[i].style.color = "white";
      }
    }


// if else to pouse and continue video

    
    var myVideo = document.getElementById("video"); 
    
    function playPause() { 
      if (myVideo.paused) 
        myVideo.play(); 
      else 
        myVideo.pause(); 
    } 
    
