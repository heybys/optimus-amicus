## βοΈ Goals

- move! move!

<br>

## π° My Plan

- Phase #1
    - [ ] Event Driven Architecture (strength and weakness νμ)
    - [ ] MSA ννλ‘ μ§νκ° κ°λ₯ν Domain Driven Design
    - [x] Multiple datasource & JPA μ€μ 
    - [x] QueryDSL μ μ© λ° λμ  μΏΌλ¦¬ μν μμ±
    - [x] Bulk Insert μ²λ¦¬
    - [x] BaseEntity κΈ°λ°μ Entity κ΅¬ν
    - [ ] λμ©λ λμμ± μ²λ¦¬ κ΅¬ν
    - [ ] API μΊμ κ΅¬ν
    - [ ] λΆμ° νΈλμ­μ κ΅¬ν
    - [ ] SNS λ‘κ·ΈμΈ
    - [ ] JWT μ μ©
    - [ ] Spring Security
    - [ ] Spring
    - ...

- Phase #2 (MSA ννλ‘ μ§ν)
    - Spring Cloud νμ© (feat. Netflix OSS)

<br>

## π Check

- Mysql
    - [x] batch insert λ°©λ² (JdbcTemplate)
- Entity
    - [x] μ»¬λΌ λ€μ΄λ° μ λ΅
    - [x] @Column μλΆμ¬λ λλμ§ μΈλμ€μ½μ΄λ‘ μ»¬λΌμμ±λλμ§
    - [x] @Table μ μ μ½μ‘°κ±΄ λ¬κΈ°
    - [x] @Column μ length λ± λ€ νκΈ°νκΈ°
    - [x] @EqualsAndHashCode νμ€νΈ. λ§€νν΄λκ³  λ μ΄μ¦ ν¨μΉλλμ§
    - [ ] @DynamicUpdate νμΈ
    - [x] @NoArgsConstructor Best Practice..
    - [X] @PrePersist, @PostPersist, Auditing νμΈ
    - [x] @UpdateTime?
    - [ ] μΌλμΌ κ΄κ³ μ, Lazy μ μ©μλλ κ²½μ° νμΈ(μλ°©ν₯ κ΄κ³ λ§Ίμ κ²½μ°)
    - [ ] Entity μμ μΌμ΄μ€
    - [ ] μΈλν€ νμΈ
    - [ ] μλ°μ΄νΈ API κ΅¬ν
- QueryDsl
    - [ ] update κ΅¬λ¬Έ νμΈ
- Etc
    - [ ] Aspect μμ  (interface, class, method)
    - [ ] check serializable transient
    - [x] dto λ..?
    - [ ] DDD..
    - [ ] μ€λΈμ νΈ
    - [ ] κ°μ²΄μ§ν₯μ μ¬μ€κ³Ό μ€ν΄
    - [ ] GOF λμμΈ ν¨ν΄
    - [ ] λ¦¬ν©ν λ§
    - [ ] JDBC Driver auto commit test

<br>

## β οΈ Precautions

- Entity μ€κ³μ κΈ°λ³Έμ λ¨λ°©ν₯ + LazyFetch
    - μ°κ΄κ΄κ³μ μ£ΌμΈμ λν΄ ν­μ μκ°!
    - μλ°©ν₯ κ΄κ³λ νμ μμλ§
    - κ΄κ³ νμ± μ, ToString() λ±μΌλ‘ μΈν μν μ°Έμ‘° μ‘°μ¬
    - λΉλν¨ν΄ μ¬μ©, setter μ¬μ© κ±°μ μμ
    - GeneratedValue ID λ§λ€ κ²
    - Entity μμ μ, @Inheritance(strategy = InheritanceType.JOINED) μ κΈ°λ³ΈμΌλ‘ ν  κ²
    - μμ κ΄κ³ λͺ¨λΈλ§μ μ°μ  μ κ·ννκ³  μ΅ λ¨μ λ°μ΄ν°κ° λμ΄κ°λ μμ λ±μμ κ°λΉ
- Controller λ¨μμ Entity -> DTO λ³νν  κ².
