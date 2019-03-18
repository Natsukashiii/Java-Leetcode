package nettydemo.provider;

import nettydemo.publicInterface.HelloService;

public class HelloServiceImpl implements HelloService {

  @Override
  public String hello(String msg) {
    return msg != null ? msg + "------------------>>>>> get Message" : " get Message";
  }
}
